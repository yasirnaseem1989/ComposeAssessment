package com.yasir.compose.assessment.ui.medicine

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.yasir.compose.assessment.data.repository.Result
import com.yasir.compose.assessment.domain.GetMedicinesUseCase
import com.yasir.compose.assessment.domain.model.Medicine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
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
class MedicineViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Mock
    private lateinit var getMedicinesUseCase: GetMedicinesUseCase

    private lateinit var viewModel: MedicineViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MedicineViewModel(getMedicinesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initial state`() = testScope.runTest {
        val expectedInitialState = MedicineUiState(isLoading = true, greetingMessage = "Good Morning")
        assertEquals(expectedInitialState, viewModel.medicinesUiState.value)
    }

    @Test
    fun `test successful data fetch`() = testScope.runTest {
        val medicines = listOf(
            Medicine(id = 1, name = "Medicine 1", dose = "Tablet", strength = "Asprin"),
            Medicine(id = 2, name = "Medicine 2", dose = "Tablet", strength = "Pandol")
        )
        `when`(getMedicinesUseCase.invoke()).thenReturn(Result.Success(medicines))

        viewModel = MedicineViewModel(getMedicinesUseCase)

        advanceUntilIdle()

        val expectedState = MedicineUiState(
            isLoading = false,
            medicinesViewItem = medicines,
            greetingMessage = "Good Morning"
        )

        assertEquals(expectedState, viewModel.medicinesUiState.value)
    }
}