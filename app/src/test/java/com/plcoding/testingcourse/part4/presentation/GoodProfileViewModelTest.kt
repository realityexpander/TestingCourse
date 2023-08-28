package com.plcoding.testingcourse.part4.presentation

import com.plcoding.testingcourse.part4.domain.IAnalyticsLogger
import com.plcoding.testingcourse.part4.domain.LogParam
import org.junit.jupiter.api.BeforeEach

internal class GoodProfileViewModelTest {

    private lateinit var viewModel: GoodProfileViewModel

    @BeforeEach
    fun setUp() {
        viewModel = GoodProfileViewModel(
            analytics = object : IAnalyticsLogger {
                override fun logEvent(key: String, vararg params: LogParam<Any>) = Unit
            }
        )
    }
}
