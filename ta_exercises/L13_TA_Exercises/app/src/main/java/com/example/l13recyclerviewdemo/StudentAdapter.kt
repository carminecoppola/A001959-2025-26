package com.example.l13recyclerviewdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.l13recyclerviewdemo.databinding.ItemStudentBinding

/**
 * StudentAdapter connects the data source to the RecyclerView.
 *
 * It extends ListAdapter, which internally uses DiffUtil to update
 * only the rows that have actually changed.
 */
class StudentAdapter(
    private val onClick: (Student) -> Unit
) : ListAdapter<Student, StudentViewHolder>(StudentDiffCallback()) {

    /**
     * Called when RecyclerView needs a new ViewHolder.
     * Thanks to recycling, this method is called only for a limited number of rows.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return StudentViewHolder(binding)
    }

    /**
     * Called when RecyclerView binds data to an existing ViewHolder.
     */
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)

        holder.bind(student)

        holder.itemView.setOnClickListener {
            onClick(student)
        }
    }
}

/**
 * DiffUtil compares old and new lists efficiently.
 *
 * areItemsTheSame checks identity.
 * areContentsTheSame checks whether the displayed data changed.
 */
class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {

    override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
        return oldItem == newItem
    }
}