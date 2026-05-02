package com.example.e05_academiccareer

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.e05_academiccareer.databinding.ItemExamBinding

class ExamAdapter : ListAdapter<ExamData, ExamAdapter.VH>(
    object : DiffUtil.ItemCallback<ExamData>() {
        override fun areItemsTheSame(a: ExamData, b: ExamData) = a.codice == b.codice
        override fun areContentsTheSame(a: ExamData, b: ExamData) = a == b
    }
) {
    class VH(private val binding: ItemExamBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(exam: ExamData) {
            binding.tvCourseName.text = exam.nome ?: "—"
            binding.tvCourseInfo.text = "${exam.cfu?.toInt() ?: "?"} CFU" +
                    (exam.status?.data?.let { " · $it" } ?: "")

            val status = exam.status
            when {
                status?.isPassed == true -> {
                    val grade = status.voto?.toInt().toString() +
                            if ((status.lode ?: 0.0) > 0) "L" else ""
                    binding.tvGrade.text = grade
                    binding.tvGrade.setTextColor(Color.parseColor("#2E7D32"))
                }
                status?.esito == "R" -> {
                    binding.tvGrade.text = "✗"
                    binding.tvGrade.setTextColor(Color.parseColor("#C62828"))
                }
                else -> {
                    binding.tvGrade.text = "—"
                    binding.tvGrade.setTextColor(Color.GRAY)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int) = VH(
        ItemExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(h: VH, pos: Int) = h.bind(getItem(pos))
}