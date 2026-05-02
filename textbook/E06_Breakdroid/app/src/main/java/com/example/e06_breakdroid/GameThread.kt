package com.example.breakdroid

import android.view.SurfaceHolder

class GameThread(
    private val holder: SurfaceHolder,
    private val gameView: GameView
) : Thread("GameThread") {

    @Volatile var running = false

    private val targetFPS = 60
    private val targetMs  = 1000L / targetFPS

    override fun run() {
        running = true
        while (running) {
            val frameStart = System.currentTimeMillis()

            gameView.update()
            gameView.draw()

            val elapsed = System.currentTimeMillis() - frameStart
            val sleepMs = targetMs - elapsed
            if (sleepMs > 0) {
                try { sleep(sleepMs) }
                catch (e: InterruptedException) { interrupt(); break }
            }
        }
    }
}