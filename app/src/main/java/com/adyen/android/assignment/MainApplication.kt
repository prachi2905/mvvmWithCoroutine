package com.adyen.android.assignment

import android.app.Application
import com.adyen.android.assignment.di.module.repoModule
import com.adyen.android.assignment.di.module.viewModelModule
import com.android.post.di.module.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(listOf(AppModule, repoModule, viewModelModule))
        }
    }
}