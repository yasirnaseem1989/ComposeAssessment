package com.yasir.compose.assessment.data.repository

import com.yasir.compose.assessment.data.local.LocalDataSource
import com.yasir.compose.assessment.data.mapper.MedicineViewItemMapper
import com.yasir.compose.assessment.data.remote.RemoteDataSource
import com.yasir.compose.assessment.domain.model.Medicine
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MedicineRepositoryTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var medicineViewItemMapper: MedicineViewItemMapper

    @InjectMocks
    private lateinit var medicineRepository: MedicineRepository

    @Before
    fun setUp() {
        medicineRepository = MedicineRepository(remoteDataSource, localDataSource, medicineViewItemMapper)
    }

    @Test
    fun `test getAllMedicines returns local data when available`() = runTest {
        // Arrange
        val localMedicines = listOf(Medicine(1, "Aspirin", "500mg", "Tablet"))
        Mockito.`when`(localDataSource.getAllMedicines()).thenReturn(localMedicines)

        // Act
        val result = medicineRepository.getAllMedicines()

        // Assert
        assertEquals(Result.Success(localMedicines), result)
        Mockito.verify(localDataSource).getAllMedicines()
        Mockito.verifyNoInteractions(remoteDataSource)
        Mockito.verifyNoInteractions(medicineViewItemMapper)
    }

    @Test
    fun `test getMedicineById returns correct medicine`() = runTest {
        // Arrange
        val medicineId = 2
        val medicine = Medicine(id = medicineId, name = "Aspirin", dose = "500mg", strength = "Tablet")
        Mockito.`when`(localDataSource.getMedicineById(2)).thenReturn(medicine)

        // Act
        val result = medicineRepository.getMedicineById(medicineId)

        // Assert
        assertEquals(Result.Success(medicine), result)
    }
}