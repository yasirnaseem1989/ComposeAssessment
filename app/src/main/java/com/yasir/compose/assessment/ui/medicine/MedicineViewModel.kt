package com.yasir.compose.assessment.ui.medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasir.compose.assessment.data.model.Medicine
import com.yasir.compose.assessment.domain.GetMedicinesUseCase
import com.yasir.compose.assessment.domain.InsertMedicineUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val getMedicinesUseCase: GetMedicinesUseCase,
    private val insertMedicineUseCase: InsertMedicineUseCase
) : ViewModel() {

    private val _medicineUiState = MutableStateFlow<List<Medicine>>(emptyList())
    val medicinesUiState = _medicineUiState.asStateFlow()

    init {
        viewModelScope.launch {
            fetchMedicines()
        }
    }

    private fun fetchMedicines() {

        getMedicinesUseCase.invoke()
            .onEach {

            }
    }
}