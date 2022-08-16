package com.adyen.android.assignment.di.module


import com.adyen.android.assignment.ui.viewmodel.AstronomyPictureListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AstronomyPictureListViewModel(get())
    }
}