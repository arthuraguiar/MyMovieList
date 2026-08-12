package br.com.mymovieslist.core.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val defaultDispatcherQualifier = named("DefaultDispatcher")
val ioDispatcherQualifier = named("IoDispatcher")
val mainDispatcherQualifier = named("MainDispatcher")

val dispatcherModule = module {
    single(defaultDispatcherQualifier) { Dispatchers.Default }
    // Dispatchers.IO isn't public API on Kotlin/Native, and all I/O in this app goes through
    // Ktor's suspend-based, non-blocking calls, so Default is used for both roles.
    single(ioDispatcherQualifier) { Dispatchers.Default }
    single(mainDispatcherQualifier) { Dispatchers.Main }
}
