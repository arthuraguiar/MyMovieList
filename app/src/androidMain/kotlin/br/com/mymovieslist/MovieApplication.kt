package br.com.mymovieslist

import android.app.Application
import br.com.mymovieslist.di.initKoin
import org.koin.android.ext.koin.androidContext

class MovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MovieApplication)
        }
    }
}
