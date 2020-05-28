package com.alphavantage.app.rule

import com.alphavantage.app.domain.widget.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutinesTestRule :
    TestWatcher() {

    val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    val scope: TestCoroutineScope = TestCoroutineScope(dispatcher)

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
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

    val testDispatcherProvider = object : DispatcherProvider {

        override fun main(): CoroutineDispatcher = dispatcher

        override fun default(): CoroutineDispatcher = dispatcher

        override fun io(): CoroutineDispatcher = dispatcher

        override fun unconfined(): CoroutineDispatcher = dispatcher
    }
}

@ExperimentalCoroutinesApi
fun CoroutinesTestRule.runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) =
    this.scope.runBlockingTest {
        block()
    }