package com.example.e05_academiccareer

import com.google.gson.annotations.SerializedName

data class AverageResponse(
    val trenta:           Double,
    @SerializedName("base_trenta")
    val baseTrenta:       Int,
    @SerializedName("base_centodieci")
    val baseCentodieci:   Int,
    val centodieci:       Double
)