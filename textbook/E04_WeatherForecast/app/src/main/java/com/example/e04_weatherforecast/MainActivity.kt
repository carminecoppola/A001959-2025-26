package com.example.e04_weatherforecast

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.e04_weatherforecast.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val vm: WeatherViewModel by viewModels()
    private val adapter = HourlyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.recyclerHourly.apply {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            this.adapter = this@MainActivity.adapter
        }

        // City spinner
        val cities = vm.placeOptions.keys.toList()
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            cities
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCity.adapter = spinnerAdapter
        binding.spinnerCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?,
                position: Int, id: Long
            ) {
                val placeCode = vm.placeOptions[cities[position]] ?: "com63049"
                vm.loadForecast(placeCode)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        vm.state.observe(this) { state ->
            when (state) {
                is WeatherViewModel.UiState.Loading -> {
                    binding.progressBar.isVisible = true
                    binding.tvError.isVisible     = false
                }
                is WeatherViewModel.UiState.Success -> {
                    binding.progressBar.isVisible = false
                    binding.tvError.isVisible     = false
                    val series = state.data.timeseries ?: emptyList()
                    adapter.submitList(series)
                    series.firstOrNull()?.let { current ->
                        binding.tvCurrentTemp.text     = "${current.t2c.toInt()}°C"
                        binding.tvCurrentDesc.text     = current.descriptionEn
                        binding.tvCurrentWind.text     = "💨 ${current.winds} ${current.ws10k.toInt()} km/h"
                        binding.tvCurrentHumidity.text = "💧 ${current.rh2.toInt()}%"
                    }
                }
                is WeatherViewModel.UiState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.tvError.isVisible     = true
                    binding.tvError.text          = state.msg
                }
                else -> {
                    binding.progressBar.isVisible = false
                }
            }
        }

        // Load Naples on start
        vm.loadForecast("com63049")
    }
}