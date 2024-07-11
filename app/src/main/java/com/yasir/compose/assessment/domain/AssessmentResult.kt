package com.yasir.compose.assessment.domain

import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class GenericError(val error: String, val code: Int) : ResultWrapper<Nothing>()
    data object NetworkError : ResultWrapper<Nothing>()
    data object Loading : ResultWrapper<Nothing>()
}

suspend fun <T> safeApiCall(apiCall: suspend () -> T): ResultWrapper<T> {
    return try {
        ResultWrapper.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
        when (throwable) {
            is IOException -> ResultWrapper.NetworkError
            is HttpException -> {
                val errorResponse = convertErrorBody(throwable)
                ResultWrapper.GenericError(errorResponse, throwable.code())
            }

            else -> {
                ResultWrapper.GenericError("Error", 500)
            }
        }
    }
}

private fun convertErrorBody(throwable: HttpException): String {
    return try {
        throwable.response()?.errorBody()?.string()?.let {
            val json = JSONObject(it)
            return json.optJSONArray("error")
                ?.optString(0)
                ?: json.optString("error", "Something went wrong")
                ?: json.optString("detail", "Something went wrong")
                ?: json.optJSONArray("email")
                    ?.optString(0)
                ?: json.optJSONArray("non_field_errors")
                    ?.optString(0)
                ?: json.optJSONArray("old_password")
                    ?.optString(0)
                ?: json.optJSONArray("new_password2")
                    ?.optString(0)
                ?: it
        } ?: "Something went wrong"
    } catch (exception: Exception) {
        "Something went wrong"
    }
}
