package com.example.breakdroid

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.breakdroid.model.*
import kotlin.math.abs

class GameView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SurfaceView(context, attrs), SurfaceHolder.Callback {

    private var gameThread: GameThread? = null

    private var screenW = 0f
    private var screenH = 0f

    private val paddleWidth   get() = screenW * 0.20f
    private val paddleHeight  get() = screenH * 0.018f
    private var paddleX = 0f
    private var paddleY = 0f

    private val ballRadius get() = screenW * 0.022f
    private var ballX  = 0f
    private var ballY  = 0f
    private var ballVX = 0f
    private var ballVY = 0f

    private val bricks = mutableListOf<Brick>()

    private var score     = 0
    private var lives     = 3
    private var level     = 1
    private var baseSpeed = 0f

    private var state: GameState = GameState.Waiting

    private val bgPaint     = Paint().apply { color = Color.BLACK }
    private val ballPaint   = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }
    private val paddlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = 0xFF00E5FF.toInt()
        style = Paint.Style.FILL
        maskFilter = BlurMaskFilter(8f, BlurMaskFilter.Blur.SOLID)
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color     = Color.WHITE
        textAlign = Paint.Align.CENTER
        typeface  = Typeface.DEFAULT_BOLD
    }
    private val hudPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color     = 0xFFBBBBBB.toInt()
        textAlign = Paint.Align.LEFT
        typeface  = Typeface.DEFAULT_BOLD
    }

    init {
        holder.addCallback(this)
        isFocusable          = true
        isFocusableInTouchMode = true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        screenW = width.toFloat()
        screenH = height.toFloat()
        initGame()
        startThread()
    }

    override fun surfaceChanged(h: SurfaceHolder, f: Int, w: Int, ht: Int) {
        screenW = w.toFloat(); screenH = ht.toFloat()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) { stopThread() }

    private fun initGame() {
        baseSpeed = screenH * 0.008f + (level - 1) * screenH * 0.001f
        paddleX   = (screenW - paddleWidth) / 2f
        paddleY   = screenH * 0.88f
        ballX     = screenW / 2f
        ballY     = paddleY - ballRadius - 2f
        ballVX    = baseSpeed * 0.6f
        ballVY    = -baseSpeed
        buildBricks()
        state = GameState.Waiting
    }

    private fun buildBricks() {
        bricks.clear()
        val cols   = 8
        val rows   = 6
        val margin = screenW * 0.02f
        val gap    = screenW * 0.015f
        val brickW = (screenW - 2 * margin - (cols - 1) * gap) / cols
        val brickH = screenH * 0.045f
        val startY = screenH * 0.10f
        val colors = BrickColor.values()

        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val left = margin + col * (brickW + gap)
                val top  = startY + row * (brickH + gap)
                bricks.add(Brick(
                    left   = left,
                    top    = top,
                    right  = left + brickW,
                    bottom = top  + brickH,
                    color  = colors[row % colors.size]
                ))
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (state == GameState.Waiting) {
                    state = GameState.Playing
                    return true
                }
                if (state is GameState.GameOver || state is GameState.LevelComplete) {
                    if (state is GameState.LevelComplete) level++
                    else { level = 1; score = 0; lives = 3 }
                    initGame()
                    state = GameState.Playing
                    return true
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (state == GameState.Playing) {
                    val targetX = event.x - paddleWidth / 2f
                    paddleX = targetX.coerceIn(0f, screenW - paddleWidth)
                }
            }
        }
        return true
    }

    fun update() {
        if (state != GameState.Playing) return

        ballX += ballVX
        ballY += ballVY

        if (ballX - ballRadius < 0f) { ballX = ballRadius; ballVX = abs(ballVX) }
        else if (ballX + ballRadius > screenW) { ballX = screenW - ballRadius; ballVX = -abs(ballVX) }

        if (ballY - ballRadius < 0f) { ballY = ballRadius; ballVY = abs(ballVY) }

        if (ballY - ballRadius > screenH) {
            lives--
            if (lives <= 0) {
                state = GameState.GameOver(score)
            } else {
                ballX  = paddleX + paddleWidth / 2f
                ballY  = paddleY - ballRadius - 2f
                ballVY = -baseSpeed
                ballVX = baseSpeed * 0.6f
                state  = GameState.Waiting
            }
            return
        }

        if (ballVY > 0 &&
            ballY + ballRadius >= paddleY &&
            ballY + ballRadius <= paddleY + paddleHeight &&
            ballX >= paddleX - ballRadius &&
            ballX <= paddleX + paddleWidth + ballRadius) {
            ballVY = -abs(ballVY)
            val paddleCentreX = paddleX + paddleWidth / 2f
            val hitOffset = (ballX - paddleCentreX) / (paddleWidth / 2f)
            ballVX = baseSpeed * hitOffset * 1.2f
        }

        for (brick in bricks) {
            if (!brick.alive) continue
            if (checkBrickCollision(brick)) {
                brick.alive = false
                score += 10
                break
            }
        }

        if (bricks.none { it.alive }) {
            state = GameState.LevelComplete(level, score)
        }
    }

    private fun checkBrickCollision(brick: Brick): Boolean {
        val closestX = ballX.coerceIn(brick.left, brick.right)
        val closestY = ballY.coerceIn(brick.top,  brick.bottom)
        val dx = ballX - closestX
        val dy = ballY - closestY
        if (dx * dx + dy * dy > ballRadius * ballRadius) return false

        val overlapLeft   = brick.right  - (ballX - ballRadius)
        val overlapRight  = (ballX + ballRadius) - brick.left
        val overlapTop    = brick.bottom - (ballY - ballRadius)
        val overlapBottom = (ballY + ballRadius) - brick.top

        val minOverlapH = minOf(overlapLeft, overlapRight)
        val minOverlapV = minOf(overlapTop,  overlapBottom)

        if (minOverlapH < minOverlapV) ballVX = -ballVX
        else ballVY = -ballVY

        return true
    }

    fun draw() {
        val canvas = holder.lockCanvas() ?: return
        try { renderFrame(canvas) } finally { holder.unlockCanvasAndPost(canvas) }
    }

    private fun renderFrame(canvas: Canvas) {
        canvas.drawRect(0f, 0f, screenW, screenH, bgPaint)

        val brickPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        for (brick in bricks) {
            if (!brick.alive) continue
            brickPaint.color = brick.color.colorHex
            canvas.drawRoundRect(brick.left, brick.top, brick.right, brick.bottom, 8f, 8f, brickPaint)
        }

        canvas.drawRoundRect(
            paddleX, paddleY,
            paddleX + paddleWidth, paddleY + paddleHeight,
            paddleHeight / 2f, paddleHeight / 2f,
            paddlePaint
        )

        canvas.drawCircle(ballX, ballY, ballRadius, ballPaint)

        hudPaint.textSize  = screenH * 0.030f
        hudPaint.textAlign = Paint.Align.LEFT
        canvas.drawText("Score: $score", screenW * 0.04f, screenH * 0.055f, hudPaint)
        hudPaint.textAlign = Paint.Align.CENTER
        canvas.drawText("Level $level", screenW / 2f, screenH * 0.055f, hudPaint)
        hudPaint.textAlign = Paint.Align.RIGHT
        canvas.drawText("♥ ".repeat(lives), screenW * 0.96f, screenH * 0.055f, hudPaint)

        when (val st = state) {
            is GameState.Waiting      -> drawOverlay(canvas, "TAP TO LAUNCH", null)
            is GameState.GameOver     -> drawOverlay(canvas, "GAME OVER", "Final Score: ${st.score}\nTap to restart")
            is GameState.LevelComplete -> drawOverlay(canvas, "LEVEL ${st.level} CLEAR!", "Score: ${st.score}\nTap for next level")
            else -> {}
        }
    }

    private fun drawOverlay(canvas: Canvas, title: String, subtitle: String?) {
        val overlayPaint = Paint().apply { color = 0xAA000000.toInt() }
        canvas.drawRect(0f, screenH * 0.35f, screenW, screenH * 0.65f, overlayPaint)

        textPaint.textSize = screenH * 0.060f
        canvas.drawText(title, screenW / 2f, screenH * 0.46f, textPaint)

        if (subtitle != null) {
            textPaint.textSize = screenH * 0.030f
            subtitle.split("\n").forEachIndexed { i, line ->
                canvas.drawText(line, screenW / 2f, screenH * 0.52f + i * screenH * 0.038f, textPaint)
            }
        }
    }

    fun pauseGame() { if (state == GameState.Playing) state = GameState.Paused; stopThread() }
    fun resumeGame() { if (state == GameState.Paused) state = GameState.Playing; startThread() }

    private fun startThread() { gameThread = GameThread(holder, this).also { it.start() } }

    private fun stopThread() {
        var retry = true
        gameThread?.running = false
        while (retry) {
            try { gameThread?.join(); retry = false }
            catch (e: InterruptedException) { Thread.currentThread().interrupt() }
        }
        gameThread = null
    }
}