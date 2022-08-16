package com.adyen.android.assignment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainCoroutineRule :
    TestWatcher() {

    private val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val scope: TestCoroutineScope = TestCoroutineScope(dispatcher)

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
        scope.cleanupTestCoroutines()
    }

    fun pause() {
        scope.pauseDispatcher()
    }

    fun resume() {
        scope.resumeDispatcher()
    }

}