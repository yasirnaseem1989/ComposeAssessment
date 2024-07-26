package com.yasir.compose.assessment.ui.medicine

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yasir.compose.assessment.data.repository.ErrorType.Generic
import com.yasir.compose.assessment.data.repository.Result
import com.yasir.compose.assessment.domain.GetMedicineDetailsUseCase
import com.yasir.compose.assessment.domain.model.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@OptIn(ExperimentalCoroutinesApi::class)
class MedicineDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Mock
    private lateinit var getMedicineDetailsUseCase: GetMedicineDetailsUseCase

    private lateinit var viewModel: MedicineDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MedicineDetailViewModel(getMedicineDetailsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initial state`() = testScope.runTest {
        val expectedInitialState = MedicineDetailUiState()
        assertEquals(expectedInitialState, viewModel.medicineDetailUiState.value)
    }

    @Test
    fun `test fetch medicine details successful`() = testScope.runTest {
        val medicine = Medicine(id = 2, name = "Medicine 2", dose = "Tablet", strength = "Pandol")
        `when`(getMedicineDetailsUseCase.invoke(1)).thenReturn(Result.Success(medicine))

        viewModel.setMedicineId(1)
        viewModel.fetchMedicineDetails()

        advanceUntilIdle()

        val expectedState = MedicineDetailUiState(
            isLoading = false,
            medicine = medicine
        )

        assertEquals(expectedState, viewModel.medicineDetailUiState.value)
    }

    @Test
    fun `test fetch medicine details failure`() = testScope.runTest {
        val error = Result.Error(Generic("Medicine not found"))
        `when`(getMedicineDetailsUseCase.invoke(1)).thenReturn(error)

        viewModel.setMedicineId(1)
        viewModel.fetchMedicineDetails()

        advanceUntilIdle()

        val expectedState = MedicineDetailUiState(
            isLoading = false,
            isError = true,
            errorType = error.exception
        )

        assertEquals(expectedState, viewModel.medicineDetailUiState.value)
    }
}