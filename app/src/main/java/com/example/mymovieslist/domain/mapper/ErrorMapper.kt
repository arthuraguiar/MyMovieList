package com.example.mymovieslist.domain.mapper

import com.example.mymovieslist.domain.model.RequestExceptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun <T> Flow<T>.parseException(): Flow<T> =
    this.catch { throwable ->
        throw when (throwable) {
            is UnknownHostException,
            is TimeoutException,
            is InterruptedIOException,
            is SocketTimeoutException,
            is SocketException,
            is ConnectException -> RequestExceptions.NoConnectionException
            is HttpException -> RequestExceptions.HttpError(
                errorMessage = throwable.message(),
                code = throwable.code()
            )
            else -> RequestExceptions.GenericError
        }
    }