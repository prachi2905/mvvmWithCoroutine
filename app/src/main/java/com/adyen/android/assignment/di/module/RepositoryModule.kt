package com.adyen.android.assignment.di.module

import com.adyen.android.assignment.api.repo.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}