package com.yasir.compose.assessment.ui.medicine

import com.yasir.compose.assessment.data.repository.Result
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasir.compose.assessment.data.repository.ErrorType
import com.yasir.compose.assessment.data.repository.ErrorType.Unknown
import com.yasir.compose.assessment.domain.GetMedicinesUseCase
import com.yasir.compose.assessment.domain.model.Medicine
import com.yasir.compose.assessment.utils.AssessmentUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val getMedicinesUseCase: GetMedicinesUseCase,
) : ViewModel() {

    private val _medicineUiState = MutableStateFlow(MedicineUiState())
    val medicinesUiState = _medicineUiState.asStateFlow()

    init {
        viewModelScope.launch {
            val greetingMessage = AssessmentUtils.getGreetingMessage()
            _medicineUiState.update { medicineUiState ->
                medicineUiState.copy(
                    isLoading = true,
                    greetingMessage = greetingMessage
                )
            }
            when (val medicines = getMedicinesUseCase.invoke()) {
                is Result.Success -> {
                    _medicineUiState.update { medicineUiState ->
                        medicineUiState.copy(
                            isLoading = false,
                            medicinesViewItem = medicines.data
                        )
                    }
                }

                is Result.Error -> {
                    _medicineUiState.update { medicineUiState ->
                        medicineUiState.copy(
                            isLoading = false,
                            isError = true,
                            errorType = medicines.exception
                        )
                    }
                }
            }
        }
    }
}

data class MedicineUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorType: ErrorType = Unknown,
    val medicinesViewItem: List<Medicine> = emptyList(),
    val greetingMessage: String = ""
) {
    fun hasData(): Boolean = medicinesViewItem.isNotEmpty()
}