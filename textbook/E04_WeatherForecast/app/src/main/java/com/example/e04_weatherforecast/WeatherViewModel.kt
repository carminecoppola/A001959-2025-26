package com.example.e04_weatherforecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class WeatherViewModel : ViewModel() {

    sealed class UiState {
        object Idle                                    : UiState()
        object Loading                                 : UiState()
        data class Success(val data: WeatherResponse)  : UiState()
        data class Error(val msg: String)              : UiState()
    }

    private val _state = MutableLiveData<UiState>(UiState.Idle)
    val state: LiveData<UiState> = _state

    val placeOptions = mapOf(
        "Naples"  to "com63049",
        "Rome"    to "com58091",
        "Milan"   to "com15146",
        "Salerno" to "com65116"
    )

    fun loadForecast(placeCode: String = "com63049") {
        _state.value = UiState.Loading
        viewModelScope.launch {
            try {
                val response = MeteoRetrofitClient.api.getForecast(placeCode)
                if (response.result == "ok" && !response.timeseries.isNullOrEmpty()) {
                    _state.value = UiState.Success(response)
                } else {
                    _state.value = UiState.Error("No forecast data available")
                }
            } catch (e: HttpException) {
                _state.value = UiState.Error("HTTP error ${e.code()}: ${e.message()}")
            } catch (e: IOException) {
                _state.value = UiState.Error("Network error — check internet connection")
            } catch (e: Exception) {
                _state.value = UiState.Error("Unexpected error: ${e.message}")
            }
        }
    }
}