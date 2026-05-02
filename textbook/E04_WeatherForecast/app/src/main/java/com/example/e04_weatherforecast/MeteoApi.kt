package com.example.e04_weatherforecast

import retrofit2.http.GET
import retrofit2.http.Path

interface MeteoApi {
    @GET("products/wrf5/timeseries/{place}")
    suspend fun getForecast(
        @Path("place") place: String
    ): WeatherResponse
}