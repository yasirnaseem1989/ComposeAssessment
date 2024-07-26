package com.yasir.compose.assessment.domain

import com.yasir.compose.assessment.data.repository.ErrorType.Generic
import com.yasir.compose.assessment.data.repository.ErrorType.Http
import com.yasir.compose.assessment.data.repository.ErrorType.Network
import com.yasir.compose.assessment.data.repository.MedicineRepository
import com.yasir.compose.assessment.data.repository.Result
import com.yasir.compose.assessment.domain.model.Medicine
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetMedicinesUseCaseTest {

    @Mock
    private lateinit var medicineRepository: MedicineRepository

    @InjectMocks
    private lateinit var getMedicinesUseCase: GetMedicinesUseCase

    @Test
    fun `test invoke returns medicines from repository`() = runTest {
        // Arrange
        val expectedMedicines = listOf(
            Medicine(1, "Aspirin", "500mg", "Strong")
        )
        Mockito.`when`(medicineRepository.getAllMedicines()).thenReturn(Result.Success(expectedMedicines))

        // Act
        val result = getMedicinesUseCase.invoke()

        // Asset
        assertEquals(Result.Success(expectedMedicines), result)
        Mockito.verify(medicineRepository).getAllMedicines()
    }

    @Test
    fun `test invoke returns empty list when no medicines in repository`() = runTest {
        // Arrange
        val expectedMedicines = emptyList<Medicine>()
        Mockito.`when`(medicineRepository.getAllMedicines()).thenReturn(Result.Success(expectedMedicines))

        // Act
        val result = getMedicinesUseCase.invoke()

        // Asset
        assertEquals(Result.Success(expectedMedicines), result)
        Mockito.verify(medicineRepository).getAllMedicines()
    }

    @Test
    fun `test invoke returns network error`() = runTest {
        // Arrange
        val expectedError = Network
        Mockito.`when`(medicineRepository.getAllMedicines()).thenReturn(Result.Error(expectedError))

        // Act
        val result = getMedicinesUseCase.invoke()

        // Assert
        assertEquals(Result.Error(expectedError), result)
        Mockito.verify(medicineRepository).getAllMedicines()
    }

    @Test
    fun `test invoke returns http error`() = runTest {
        // Arrange
        val expectedError = Http(404, "Not Found")
        Mockito.`when`(medicineRepository.getAllMedicines()).thenReturn(Result.Error(expectedError))

        // Act
        val result = getMedicinesUseCase.invoke()

        // Assert
        assertEquals(Result.Error(expectedError), result)
        Mockito.verify(medicineRepository).getAllMedicines()
    }

    @Test
    fun `test invoke returns generic error`() = runTest {
        // Arrange
        val expectedError = Generic("Medicine not found")
        Mockito.`when`(medicineRepository.getAllMedicines()).thenReturn(Result.Error(expectedError))

        // Act
        val result = getMedicinesUseCase.invoke()

        // Assert
        assertEquals(Result.Error(expectedError), result)
        Mockito.verify(medicineRepository).getAllMedicines()
    }
}