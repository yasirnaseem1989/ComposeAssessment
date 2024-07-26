package com.yasir.compose.assessment.data.remote

import com.yasir.compose.assessment.data.repository.ErrorType
import com.yasir.compose.assessment.data.repository.Result
import com.yasir.compose.assessment.data.repository.Result.Error
import com.yasir.compose.assessment.data.repository.Result.Success
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NetworkHandler @Inject constructor() {

    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
        return try {
            Success(apiCall.invoke())
        } catch (e: Exception) {
            Error(mapError(e))
        }
    }

    private fun mapError(e: Exception): ErrorType {
        return when (e) {
            is IOException -> ErrorType.Network
            is HttpException -> ErrorType.Http(e.code(), e.message())
            else -> ErrorType.Unknown
        }
    }
}