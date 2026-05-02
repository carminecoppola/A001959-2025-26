package com.example.e04_weatherforecast

data class WeatherResponse(
    val result:     String,
    val timeseries: List<HourlyEntry>?
)