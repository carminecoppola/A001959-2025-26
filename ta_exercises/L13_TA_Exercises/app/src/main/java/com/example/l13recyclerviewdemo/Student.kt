package com.example.l13recyclerviewdemo

/**
 * Student represents a single item displayed by the RecyclerView.
 *
 * The id field is important because DiffUtil uses it to understand
 * whether two objects represent the same logical entity.
 */
data class Student(
    val id: Int,
    val name: String,
    val course: String
)