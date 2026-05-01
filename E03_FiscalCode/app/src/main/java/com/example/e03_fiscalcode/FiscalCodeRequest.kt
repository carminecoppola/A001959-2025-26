package com.example.e03_fiscalcode

data class FiscalCodeRequest(
    val surname: String,
    val name: String,
    val birthdate: String,   // "YYYY-MM-DD"
    val sex: String,         // "M" or "F"
    val birthplace: String
)