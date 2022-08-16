package com.adyen.android.assignment


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.adyen.android.assignment.di.module.repoModule
import com.adyen.android.assignment.di.module.viewModelModule
import com.android.post.di.module.AppModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules


class KoinModuleTest : KoinTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            modules(listOf(viewModelModule, AppModule, repoModule))
        }.checkModules()
    }
}