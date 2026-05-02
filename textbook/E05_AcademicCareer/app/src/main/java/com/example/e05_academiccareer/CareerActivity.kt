package com.example.e05_academiccareer

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e05_academiccareer.databinding.ActivityCareerBinding
import com.google.android.material.snackbar.Snackbar

class CareerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCareerBinding
    private val vm: CareerViewModel by viewModels()
    private val adapter = ExamAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Prevent screenshots on career screen (privacy)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )

        binding = ActivityCareerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.recyclerExams.apply {
            layoutManager = LinearLayoutManager(this@CareerActivity)
            this.adapter  = this@CareerActivity.adapter
        }

        binding.btnLogout.setOnClickListener {
            vm.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        vm.state.observe(this) { state ->
            when (state) {
                is CareerViewModel.UiState.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is CareerViewModel.UiState.Success -> {
                    binding.progressBar.isVisible = false
                    val d = state.data
                    binding.tvName.text    = "${d.user.firstName} ${d.user.lastName}"
                    binding.tvId.text      = "Matricola: ${d.career.matricola}"
                    binding.tvDegree.text  = d.career.cdsDes
                    binding.tvCredits.text = "${d.totals.cfuPar.toInt()}/${d.totals.cfuTot.toInt()} CFU"
                    binding.tvAverage.text = String.format("%.2f/30 (%.2f/110)",
                        d.average.trenta, d.average.centodieci)
                    adapter.submitList(
                        d.exams.sortedWith(
                            compareBy({ it.status?.isPassed != true }, { it.nome })
                        )
                    )
                }
                is CareerViewModel.UiState.Error -> {
                    binding.progressBar.isVisible = false
                    if (state.auth) {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Snackbar.make(binding.root, state.msg, Snackbar.LENGTH_LONG).show()
                    }
                }
                else -> binding.progressBar.isVisible = false
            }
        }

        vm.checkExistingSession()
    }
}