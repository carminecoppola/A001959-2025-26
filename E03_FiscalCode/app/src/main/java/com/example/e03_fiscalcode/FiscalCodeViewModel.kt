package com.example.e03_fiscalcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class FiscalCodeViewModel : ViewModel() {

    sealed class UiState {
        object Idle                            : UiState()
        object Loading                         : UiState()
        data class Success(val code: String)   : UiState()
        data class Error(val msg: String)      : UiState()
    }

    private val _state = MutableLiveData<UiState>(UiState.Idle)
    val state: LiveData<UiState> = _state

    fun compute(
        surname: String,
        name: String,
        birthdate: String,
        sex: String,
        birthplace: String
    ) {
        if (surname.isBlank() || name.isBlank() ||
            birthdate.isBlank() || birthplace.isBlank()
        ) {
            _state.value = UiState.Error("Tutti i campi sono obbligatori")
            return
        }

        _state.value = UiState.Loading

        viewModelScope.launch {
            try {
                val response = RetrofitClient.api.compute(
                    FiscalCodeRequest(surname, name, birthdate, sex, birthplace)
                )
                _state.value = when {
                    response.fiscal_code != null -> UiState.Success(response.fiscal_code)
                    response.error != null       -> UiState.Error(response.error)
                    else                         -> UiState.Error("Risposta vuota dal server")
                }
            } catch (e: HttpException) {
                _state.value = UiState.Error("Errore server: ${e.code()}")
            } catch (e: IOException) {
                _state.value = UiState.Error("Errore di rete — il server è in esecuzione?")
            }
        }
    }
}