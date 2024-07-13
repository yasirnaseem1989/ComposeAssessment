package com.yasir.compose.assessment.di

import android.app.Application
import androidx.room.Room
import com.yasir.compose.assessment.data.local.LocalDataSource
import com.yasir.compose.assessment.data.local.LocalMedicineDataSource
import com.yasir.compose.assessment.data.local.MedicineDao
import com.yasir.compose.assessment.data.local.MedicineDatabase
import com.yasir.compose.assessment.data.mapper.MedicineMapper
import com.yasir.compose.assessment.data.remote.MedicineApiService
import com.yasir.compose.assessment.data.remote.RemoteDataSource
import com.yasir.compose.assessment.data.remote.RemoteMedicineDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MedicineDatabase {
        return Room.databaseBuilder(app, MedicineDatabase::class.java, "medicine_db")
            .build()
    }

    @Provides
    fun provideMedicineDao(db: MedicineDatabase): MedicineDao {
        return db.medicineDao()
    }

    @Provides
    @Singleton
    fun provideMedicineRemoteDataSource(
        apiService: MedicineApiService,
        medicineMapper: MedicineMapper,
    ): RemoteDataSource =
        RemoteMedicineDataSource(apiService, medicineMapper)


    @Provides
    @Singleton
    fun provideLocalMedicineDataSource(medicineDao: MedicineDao): LocalDataSource =
        LocalMedicineDataSource(medicineDao)
}