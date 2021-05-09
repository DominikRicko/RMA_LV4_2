package hr.dominikricko.rma_lv4_2.di

import hr.dominikricko.rma_lv4_2.data.api.ApiHelper
import hr.dominikricko.rma_lv4_2.data.api.ApiServiceImpl
import hr.dominikricko.rma_lv4_2.data.repository.MainRepository
import hr.dominikricko.rma_lv4_2.ui.main.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}