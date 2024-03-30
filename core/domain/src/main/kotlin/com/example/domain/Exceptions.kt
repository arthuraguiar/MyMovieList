package com.example.domain

sealed class RequestExceptions : Throwable() {
    data object NoConnectionException : RequestExceptions()
    data class HttpError(val errorMessage: String, val code: Int) : RequestExceptions()
    data object GenericError : RequestExceptions()
}

fun thisShouldBeaLongMethod(): Int {
    val thisIsACodeSmell = 45224
    val x = 89
    val y = 2
    val z = x + y
    val DJDJDJJD = 0











    






    return 45 + 0
}
