package com.example.notification

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * Worker class to handle background tasks for notifications.
 * Schedule Notifications with WorkManager
 */
class NotificationWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        // Fetch data passed to the worker
        val title = inputData.getString("title") ?: "Default Title"
        val message = inputData.getString("message") ?: "Default Message"

        // Show the notification
        showNotification(applicationContext, title, message)

        return Result.success()
    }
}
