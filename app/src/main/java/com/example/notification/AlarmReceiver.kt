package com.example.notification

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/*
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_SHORT).show()
        // Handle notification or other actions here
        val title = intent.getStringExtra("title") ?: "Default Title"
        val message = intent.getStringExtra("message") ?: "Default Message"

        val channelId = "channelId"
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        // Check for notification permission (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permissionPOST_NOTIFICATIONs)
                != PackageManager.PERMISSION_GRANTED
            ) {
                // Request permission if not granted
                ActivityCompat.requestPermissions(
                    (context as? android.app.Activity) ?: return,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
                return
            }
        }

        NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
    }
}*/

// AlarmReceiver to handle the alarm action
class AlarmReceiver : android.content.BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Toast.makeText(context, "Alarm Triggered!", Toast.LENGTH_SHORT).show()
        if (context != null) {
            showNotification(context, "Alarm Triggered!", "This is a scheduled alarm notification.")
        }
    }

    // Show the notification
    @SuppressLint("MissingPermission")
    private fun showNotification(context: Context, title: String, message: String) {
        val notification = NotificationCompat.Builder(context, "channelId")
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        // Check for notification permission (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // Request permission if not granted
                ActivityCompat.requestPermissions((context as? android.app.Activity) ?: return, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 0)
                return
            }
        }
        NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
    }
}
