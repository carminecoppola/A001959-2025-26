package com.example.e05_academiccareer

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class CareerViewModel(application: Application) : AndroidViewModel(application) {

    private val sessionManager = SessionManager(application)

    data class CareerUiData(
        val user:       UserData,
        val career:     TrattoCarriera,
        val exams:      List<ExamData>,
        val totals:     TotalExamsResponse,
        val average:    AverageResponse
    )

    sealed class UiState {
        object Idle                                          : UiState()
        object Loading                                       : UiState()
        data class Success(val data: CareerUiData)           : UiState()
        data class Error(val msg: String, val auth: Boolean = false) : UiState()
    }

    private val _state = MutableLiveData<UiState>(UiState.Idle)
    val state: LiveData<UiState> = _state

    fun login(userId: String, password: String) {
        if (userId.isBlank() || password.isBlank()) {
            _state.value = UiState.Error("Inserisci userId e password")
            return
        }
        sessionManager.saveCredentials(userId, password)
        loadCareer()
    }

    fun loadCareer() {
        val auth = sessionManager.getAuthHeader() ?: run {
            _state.value = UiState.Error("Non autenticato", auth = true)
            return
        }
        _state.value = UiState.Loading

        viewModelScope.launch {
            try {
                // 1. Login
                val loginResp = UnipaRetrofitClient.api.login(auth)
                val user      = loginResp.user
                val career    = user.trattiCarriera.firstOrNull() ?: run {
                    _state.value = UiState.Error("Nessuna carriera trovata")
                    return@launch
                }
                val matId = career.matId

                // 2. Fetch exams, totals, average in parallel
                val examsDeferred   = async { UnipaRetrofitClient.api.getMyExams(auth, matId) }
                val totalsDeferred  = async { UnipaRetrofitClient.api.getTotalExams(auth, matId) }
                val averageDeferred = async { UnipaRetrofitClient.api.getAverage(auth, matId, "ponderata") }

                val exams   = examsDeferred.await()
                val totals  = totalsDeferred.await()
                val average = averageDeferred.await()

                _state.value = UiState.Success(
                    CareerUiData(user, career, exams, totals, average)
                )
            } catch (e: HttpException) {
                if (e.code() == 401 || e.code() == 403) {
                    sessionManager.clearSession()
                    _state.value = UiState.Error("Credenziali non valide", auth = true)
                } else {
                    _state.value = UiState.Error("Errore server ${e.code()}")
                }
            } catch (e: IOException) {
                _state.value = UiState.Error("Errore di rete — controlla la connessione")
            } catch (e: Exception) {
                _state.value = UiState.Error("Errore: ${e.message}")
            }
        }
    }

    fun logout() {
        sessionManager.clearSession()
        _state.value = UiState.Idle
    }

    fun checkExistingSession() {
        if (sessionManager.isLoggedIn()) loadCareer()
    }
}