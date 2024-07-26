package com.yasir.compose.assessment.data.repository

sealed class ErrorType {
    data object Network : ErrorType()
    data class Http(val code: Int, val message: String) : ErrorType()
    data class Generic(val message: String) : ErrorType()
    data object Unknown : ErrorType()
}