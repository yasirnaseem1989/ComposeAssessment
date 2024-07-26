package com.yasir.compose.assessment.ui.medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasir.compose.assessment.data.repository.ErrorType
import com.yasir.compose.assessment.data.repository.ErrorType.Unknown
import com.yasir.compose.assessment.data.repository.Result
import com.yasir.compose.assessment.domain.GetMedicineDetailsUseCase
import com.yasir.compose.assessment.domain.model.Medicine
import com.yasir.compose.assessment.utils.ext.orFalse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineDetailViewModel @Inject constructor(
    private val getMedicineDetailsUseCase: GetMedicineDetailsUseCase,
) : ViewModel() {


    private val _medicineDetailUiState = MutableStateFlow(MedicineDetailUiState())
    val medicineDetailUiState = _medicineDetailUiState.asStateFlow()

    private var medicineId: Int = 0


    fun fetchMedicineDetails() {
        _medicineDetailUiState.update { medicineUiState ->
            medicineUiState.copy(
                isLoading = true,
            )
        }
        viewModelScope.launch {


            when (val fetchedMedicine = getMedicineDetailsUseCase.invoke(medicineId)) {
                is Result.Success -> {
                    _medicineDetailUiState.update { medicineUiState ->
                        medicineUiState.copy(
                            isLoading = false,
                            medicine = fetchedMedicine.data
                        )
                    }
                }
                is Result.Error -> {
                    _medicineDetailUiState.update { medicineUiState ->
                        medicineUiState.copy(
                            isLoading = false,
                            isError = true,
                            errorType = fetchedMedicine.exception
                        )
                    }
                }
            }
        }
    }

    fun setMedicineId(medicineId: Int) {
        this.medicineId = medicineId
    }
}

data class MedicineDetailUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val medicine: Medicine? = null,
    val greetingMessage: String = "",
    val errorType: ErrorType = Unknown,
) {
    fun hasData(): Boolean = medicine?.hasValidId().orFalse()
}