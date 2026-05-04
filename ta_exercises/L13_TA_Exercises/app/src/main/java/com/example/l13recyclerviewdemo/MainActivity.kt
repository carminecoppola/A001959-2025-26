package com.example.l13recyclerviewdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.l13recyclerviewdemo.databinding.ActivityMainBinding

/**
 * MainActivity — RecyclerView Demo.
 *
 * This Activity demonstrates the core RecyclerView architecture:
 * - RecyclerView as the list container;
 * - LinearLayoutManager for vertical list positioning;
 * - Adapter for connecting data to the UI;
 * - ViewHolder for efficient row reuse;
 * - ListAdapter and DiffUtil for efficient updates.
 */
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var studentAdapter: StudentAdapter

    companion object {
        private const val TAG = "L13_RECYCLER_VIEW"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        submitInitialList()
    }

    /**
     * Configures the RecyclerView with its LayoutManager, Adapter and decoration.
     */
    private fun setupRecyclerView() {
        studentAdapter = StudentAdapter { selectedStudent ->
            onStudentClicked(selectedStudent)
        }

        binding.recyclerViewStudents.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )

            adapter = studentAdapter
        }
    }

    /**
     * Provides a static academic dataset for the demonstration.
     *
     * submitList is the correct way to update a ListAdapter.
     */
    private fun submitInitialList() {
        val students = listOf(
            Student(1, "Alice Rossi", "Mobile Programming"),
            Student(2, "Marco Bianchi", "Android Fundamentals"),
            Student(3, "Giulia Verdi", "Kotlin Basics"),
            Student(4, "Luca Esposito", "RecyclerView"),
            Student(5, "Sara Romano", "ViewHolder Pattern"),
            Student(6, "Francesco Gallo", "DiffUtil"),
            Student(7, "Anna Ferri", "UI Architecture"),
            Student(8, "Paolo Conti", "Jetpack Components")
        )

        studentAdapter.submitList(students)
    }

    /**
     * Handles item click events.
     *
     * This demonstrates how RecyclerView rows can communicate user actions
     * back to the Activity through a lambda callback.
     */
    private fun onStudentClicked(student: Student) {
        Log.d(TAG, "Selected student: ${student.name}")

        binding.tvSelectedStudent.text =
            "Selected: ${student.name} — ${student.course}"
    }
}