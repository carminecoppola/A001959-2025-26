package com.example.e04_weatherforecast

import com.google.gson.annotations.SerializedName

data class WeatherText(
    @SerializedName("en-US") val enUS: String?,
    @SerializedName("it-IT") val itIT: String?
)