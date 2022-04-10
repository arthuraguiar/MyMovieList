package com.example.mymovieslist.domain.model

sealed class RequestExceptions : Throwable() {
    object NoConnectionException : RequestExceptions()
    data class HttpError(val errorMessage: String, val code: Int): RequestExceptions()
    object GenericError : RequestExceptions()
}
