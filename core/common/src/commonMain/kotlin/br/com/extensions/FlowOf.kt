package br.com.extensions

import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import kotlin.experimental.ExperimentalTypeInference

@OptIn(ExperimentalTypeInference::class)
fun <T> flowOf(block: suspend FlowCollector<T>.() -> T): Flow<T> =
    flow { emit(block()) }.parseException()

private fun <T> Flow<T>.parseException(): Flow<T> =
    this.catch { throwable ->
        throw when (throwable) {
            is ResponseException -> RequestExceptions.HttpError(
                errorMessage = throwable.message.orEmpty(),
                code = throwable.response.status.value
            )
            is IOException -> RequestExceptions.NoConnectionException()
            else -> RequestExceptions.GenericError()
        }
    }
