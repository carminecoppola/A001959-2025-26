package com.example.e03_fiscalcode

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FiscalCodeApi {

    @POST("api/v1/fiscal-code")
    suspend fun compute(@Body request: FiscalCodeRequest): FiscalCodeResponse

    @GET("health")
    suspend fun health(): Map<String, String>
}