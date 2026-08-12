package br.com.mymovieslist.di

import br.com.mymovieslist.core.di.ioDispatcherQualifier
import br.com.mymovieslist.presentation.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get(), get(ioDispatcherQualifier)) }
}
