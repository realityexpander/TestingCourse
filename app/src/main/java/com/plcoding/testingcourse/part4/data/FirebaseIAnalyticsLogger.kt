package com.plcoding.testingcourse.part4.data

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import com.plcoding.testingcourse.part4.domain.IAnalyticsLogger
import com.plcoding.testingcourse.part4.domain.LogParam

// wrapper around FirebaseAnalytics
class FirebaseIAnalyticsLogger(
    private val analytics: FirebaseAnalytics = Firebase.analytics
): IAnalyticsLogger {

    override fun logEvent(key: String, vararg params: LogParam<Any>) {
        analytics.logEvent("save_profile") {
            params.forEach { parameter ->
                param(parameter.key, parameter.value.toString())
            }
        }
    }
}
