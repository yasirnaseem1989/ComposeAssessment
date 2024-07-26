package com.yasir.compose.assessment.data.repository

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: ErrorType) : Result<Nothing>()
}