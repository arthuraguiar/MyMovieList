package br.com.mymovieslist.di

import br.com.domain.di.domainModule
import br.com.mymovieslist.core.di.dispatcherModule
import br.com.mymovieslist.dataremote.network.di.dataRemoteModule
import br.com.mymovieslist.dataremote.network.di.networkModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            domainModule,
            dataRemoteModule,
            networkModule,
            dataModule,
            dispatcherModule,
            appModule,
        )
    }
}
