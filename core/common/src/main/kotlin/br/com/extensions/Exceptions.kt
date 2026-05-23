package br.com.extensions

sealed class RequestExceptions : Throwable() {
    class NoConnectionException : RequestExceptions()
    data class HttpError(val errorMessage: String, val code: Int) : RequestExceptions()
    class GenericError : RequestExceptions()
}
