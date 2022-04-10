package com.example.mymovieslist

import android.app.Application
import com.example.mymovieslist.core.di.appModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalSerializationApi

class MovieApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MovieApplication)
            modules(appModule)
        }
    }
}