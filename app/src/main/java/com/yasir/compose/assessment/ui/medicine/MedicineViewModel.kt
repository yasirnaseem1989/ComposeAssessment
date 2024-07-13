package com.yasir.compose.assessment.ui.medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
            val result = getMedicinesUseCase.invoke()
            _medicineUiState.update { medicineUiState ->
                medicineUiState.copy(
                    isLoading = false,
                    medicinesViewItem = result
                )
            }
        }
    }
}

data class MedicineUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val medicinesViewItem: List<Medicine> = emptyList(),
    val greetingMessage: String = ""
) {
    fun hasData(): Boolean = medicinesViewItem.isNotEmpty()
}