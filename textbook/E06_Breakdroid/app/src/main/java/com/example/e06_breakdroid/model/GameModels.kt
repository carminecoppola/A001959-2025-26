package com.example.breakdroid.model

enum class BrickColor(val colorHex: Int) {
    RED(0xFFE53935.toInt()),
    ORANGE(0xFFFB8C00.toInt()),
    YELLOW(0xFFFDD835.toInt()),
    GREEN(0xFF43A047.toInt()),
    BLUE(0xFF1E88E5.toInt()),
    PURPLE(0xFF8E24AA.toInt())
}

data class Brick(
    var left:   Float,
    var top:    Float,
    var right:  Float,
    var bottom: Float,
    val color:  BrickColor,
    var alive:  Boolean = true
)

sealed class GameState {
    object Waiting                                  : GameState()
    object Playing                                  : GameState()
    object Paused                                   : GameState()
    data class GameOver(val score: Int)             : GameState()
    data class LevelComplete(val level: Int, val score: Int) : GameState()
}