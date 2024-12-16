package com.example.notification

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create notification channel
        createNotificationChannel(this)

        findViewById<Button>(R.id.btn).setOnClickListener {
            //Handler(Looper.getMainLooper()).postDelayed({}, 5000L)
            // Schedule a notification
            scheduleNotification(1, "Reminder", "This is your scheduled notification!")
        }

    }

    /**
     * Schedules a notification using WorkManager after a specified delay.
     */
    private fun scheduleNotification(delayInMinutes: Long, title: String, message: String) {
        val workRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(delayInMinutes, TimeUnit.MINUTES)
            .setInputData(
                workDataOf(
                    "title" to title,
                    "message" to message
                )
            )
            .build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}

