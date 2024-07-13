package com.yasir.compose.assessment.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _loginUiState = MutableStateFlow(LoginUiState())
    val loginUiState = _loginUiState.asStateFlow()

    fun login(user: String) {
        if (user.isEmpty()) {
            _loginUiState.update { feedingStats ->
                feedingStats.copy(isError = true)
            }
        } else {
            _loginUiState.update { feedingStats ->
                feedingStats.copy(
                    isError = false,
                    isSuccess = true
                )
            }
        }
    }
}

data class LoginUiState(
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)