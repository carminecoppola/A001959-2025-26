package com.example.l13recyclerviewdemo

import androidx.recyclerview.widget.RecyclerView
import com.example.l13recyclerviewdemo.databinding.ItemStudentBinding

/**
 * StudentViewHolder stores references to the views of a single row.
 *
 * This follows the ViewHolder pattern explained in the lesson:
 * instead of repeatedly searching views, the ViewHolder keeps them ready.
 */
class StudentViewHolder(
    private val binding: ItemStudentBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds one Student object to the row layout.
     */
    fun bind(student: Student) {
        binding.tvStudentName.text = student.name
        binding.tvStudentCourse.text = student.course
    }
}