package com.yasir.compose.assessment.di

import com.yasir.compose.assessment.data.remote.MedicineApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().apply {
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
                addInterceptor(logging)
        }.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideMedicineApiService(retrofit: Retrofit): MedicineApiService {
        return retrofit.create(MedicineApiService::class.java)
    }
}