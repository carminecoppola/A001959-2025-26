package com.example.e05_academiccareer

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    val user: UserData
)

data class UserData(
    val userId:         String?,
    val firstName:      String?,
    val lastName:       String?,
    val codFis:         String,
    val grpDes:         String,   // "Studenti" or "Docenti"
    val persId:         Int?,
    val trattiCarriera: List<TrattoCarriera>
)

data class TrattoCarriera(
    val matId:     Int,
    val matricola: String,
    val cdsDes:    String,   // nome del corso di laurea
    val stuId:     Int
)