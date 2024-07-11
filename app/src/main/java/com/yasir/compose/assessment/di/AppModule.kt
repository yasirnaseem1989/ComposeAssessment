package com.yasir.compose.assessment.di

import android.app.Application
import androidx.room.Room
import com.yasir.compose.assessment.data.local.LocalDataSource
import com.yasir.compose.assessment.data.local.MedicineDao
import com.yasir.compose.assessment.data.local.MedicineDatabase
import com.yasir.compose.assessment.data.remote.MedicineApiService
import com.yasir.compose.assessment.data.remote.RemoteDataSource
import com.yasir.compose.assessment.data.repository.MedicineRepository
import com.yasir.compose.assessment.data.repository.MedicineRepositoryImpl
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
    fun provideMedicineRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): MedicineRepository {
        return MedicineRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(medicineDao: MedicineDao): LocalDataSource {
        return LocalDataSource(medicineDao)
    }

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: MedicineApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }
}