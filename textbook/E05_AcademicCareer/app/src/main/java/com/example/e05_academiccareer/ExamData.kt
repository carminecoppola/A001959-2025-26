package com.example.e05_academiccareer

data class ExamData(
    val nome:    String?,
    val codice:  String?,
    @com.google.gson.annotations.SerializedName("CFU")
    val cfu:     Double?,
    val annoId:  Int?,
    val status:  ExamStatus?
)

data class ExamStatus(
    val esito: String?,   // "S" = superato, "R" = ritirato, null = non sostenuto
    val voto:  Double?,
    val lode:  Double?,
    val data:  String?    // "DD/MM/YYYY"
) {
    val isPassed: Boolean get() = esito == "S" && voto != null
}