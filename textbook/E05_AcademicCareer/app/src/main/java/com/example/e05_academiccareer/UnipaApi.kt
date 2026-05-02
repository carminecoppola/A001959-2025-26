package com.example.e05_academiccareer

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UnipaApi {

    // Step 1: login → restituisce User con trattiCarriera (lista carriere)
    @GET("UniparthenopeApp/v1/login")
    suspend fun login(
        @Header("Authorization") authHeader: String
    ): LoginResponse

    // Step 2: esami superati/in piano con status
    @GET("UniparthenopeApp/v2/students/myExams/{matId}")
    suspend fun getMyExams(
        @Header("Authorization") authHeader: String,
        @Path("matId") matId: Int
    ): List<ExamData>

    // Step 3: totale CFU e conteggi
    @GET("UniparthenopeApp/v1/students/totalExams/{matId}")
    suspend fun getTotalExams(
        @Header("Authorization") authHeader: String,
        @Path("matId") matId: Int
    ): TotalExamsResponse

    // Step 4: media ponderata/aritmetica
    @GET("UniparthenopeApp/v1/students/average/{matId}/{type}")
    suspend fun getAverage(
        @Header("Authorization") authHeader: String,
        @Path("matId") matId: Int,
        @Path("type") type: String = "ponderata"
    ): AverageResponse
}