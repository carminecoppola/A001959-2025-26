package com.example.e04_weatherforecast

data class HourlyEntry(
    val dateTime:  String,
    val t2c:       Double,
    val slp:       Double,
    val ws10:      Double,
    val ws10k:     Double,
    val wd10:      Double,
    val winds:     String,
    val wchill:    Int,
    val rh2:       Double,
    val clf:       Double,
    val swe:       Double,
    val beaufort:  Int,
    val icon:      String,
    val text:      WeatherText?
) {
    // "20260302Z0000" -> "02/03 00:00"
    val displayTime: String
        get() {
            if (dateTime.length < 13) return dateTime
            val d  = dateTime.substring(6, 8)
            val mo = dateTime.substring(4, 6)
            val h  = dateTime.substring(9, 11)
            val mi = dateTime.substring(11, 13)
            return "$d/$mo $h:$mi"
        }

    val weatherEmoji: String
        get() = when {
            icon.contains("sunny")   -> "☀️"
            icon.contains("rain")    -> "🌧️"
            icon.contains("snow")    -> "❄️"
            icon.contains("thunder") -> "⛈️"
            icon.contains("fog")     -> "🌫️"
            icon.contains("cloudy5") -> "☁️"
            icon.contains("cloudy")  -> "⛅"
            else                     -> "🌡️"
        }

    val descriptionEn: String
        get() = text?.enUS ?: "N/A"
}