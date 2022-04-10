package com.example

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

@ExperimentalCoroutinesApi
class CoroutinesTestRule : TestRule {

    val standardTestDispatcher = StandardTestDispatcher()

    override fun apply(base: Statement?, description: Description?) = object : Statement() {
        override fun evaluate() {
            Dispatchers.setMain(standardTestDispatcher)

            base?.evaluate()

            Dispatchers.resetMain()
        }
    }

}