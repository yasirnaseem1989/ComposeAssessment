package com.yasir.compose.assessment.data.remote

import com.yasir.compose.assessment.data.model.Medicine
import retrofit2.http.GET

interface MedicineApiService {

    @GET("v3/46819d5f-4f68-4ee4-91f9-beb9ba913cea")
    suspend fun getMedicines(): List<Medicine>
}