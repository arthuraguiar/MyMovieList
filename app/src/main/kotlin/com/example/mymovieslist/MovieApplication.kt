package com.example.mymovieslist

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@HiltAndroidApp
class MovieApplication : Application()