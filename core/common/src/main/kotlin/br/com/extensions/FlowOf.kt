package br.com.extensions

import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import kotlin.experimental.ExperimentalTypeInference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

@OptIn(ExperimentalTypeInference::class)
fun <T> flowOf(block: suspend FlowCollector<T>.() -> T): Flow<T> =
    flow { emit(block()) }.parseException()

private fun <T> Flow<T>.parseException(): Flow<T> =
    this.catch { throwable ->
        throw when (throwable) {
            is ConnectException,
            is SocketTimeoutException,
            is UnknownHostException,
            is TimeoutException,
            is InterruptedIOException,
            is SocketException, -> RequestExceptions.NoConnectionException()
            is HttpException -> RequestExceptions.HttpError(
                errorMessage = throwable.message(),
                code = throwable.code()
            )
            else -> RequestExceptions.GenericError()
        }
    }
