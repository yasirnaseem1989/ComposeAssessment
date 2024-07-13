package com.yasir.compose.assessment.domain

import com.yasir.compose.assessment.data.repository.MedicineRepository
import com.yasir.compose.assessment.domain.model.Medicine
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        Mockito.`when`(medicineRepository.getAllMedicines()).thenReturn(expectedMedicines)

        // Act
        val result = getMedicinesUseCase.invoke()

        // Asset
        assertEquals(expectedMedicines, result)
        Mockito.verify(medicineRepository).getAllMedicines()
    }

    @Test
    fun `test invoke returns empty list when no medicines in repository`() = runTest {
        // Arrange
        val expectedMedicines = emptyList<Medicine>()
        Mockito.`when`(medicineRepository.getAllMedicines()).thenReturn(expectedMedicines)

        // Act
        val result = getMedicinesUseCase.invoke()

        // Asset
        assertEquals(expectedMedicines, result)
        Mockito.verify(medicineRepository).getAllMedicines()
    }

    @Test
    fun `test invoke handles exception`() = runTest {
        // Arrange
        Mockito.`when`(medicineRepository.getAllMedicines()).thenThrow(RuntimeException("Network error"))

        // Act
        val result = runCatching { getMedicinesUseCase.invoke() }

        // Asset
        assertEquals("Network error", result.exceptionOrNull()?.message)
        Mockito.verify(medicineRepository).getAllMedicines()
    }
}