package com.example.domain

sealed class RequestExceptions : Throwable() {
    data object NoConnectionException : RequestExceptions()
    data class HttpError(val errorMessage: String, val code: Int) : RequestExceptions()
    data object GenericError : RequestExceptions()
}