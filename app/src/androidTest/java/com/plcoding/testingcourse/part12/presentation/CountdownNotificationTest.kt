package com.plcoding.testingcourse.part12.presentation

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.core.app.NotificationCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.GrantPermissionRule
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.plcoding.testingcourse.MainActivity
import com.plcoding.testingcourse.part12.data.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountdownNotificationTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val composeRule =
        createAndroidComposeRule<MainActivity>()

    // This is only needed to run on OS versions above 13
//    @get:Rule
//    val grantPermissionRule: GrantPermissionRule = if (android.os.Build.VERSION.SDK_INT >= 33)
//        GrantPermissionRule.grant(Manifest.permission.POST_NOTIFICATIONS)
//    else
//        GrantPermissionRule.grant(Manifest.permission.ACCESS_NOTIFICATION_POLICY)

    @Test
    fun testNotificationCountdown() = runTest {
        runCurrent()
        val context = ApplicationProvider.getApplicationContext<Context>()
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        for(i in 5 downTo 1) {
            composeRule.waitUntil {
                notificationManager.activeNotifications.isNotEmpty()
            }
            val notification = notificationManager.activeNotifications.first {
                it.id == 1
            }.notification

            val contentText = notification.extras.getString(NotificationCompat.EXTRA_TEXT)
            assertThat(contentText).isEqualTo("Counting down: $i")

            advanceTimeBy(1000L)
            runCurrent()
        }
        notificationManager.cancel(1)
    }
}
