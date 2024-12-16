package com.example.notification

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat




class MainActivity : AppCompatActivity() {
    private val CHANNEL_ID = "channelId"
    val CHANNEL_NAME = "channelName"
    val notificationId = 0
    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,intent, PendingIntent.FLAG_MUTABLE)

        val notification = NotificationCompat.Builder(this,CHANNEL_ID).setContentTitle("Congratulations!").setContentText("this is a notification").setSmallIcon(R.drawable.ic_launcher_foreground).setPriority(NotificationCompat.PRIORITY_HIGH).setContentIntent(pendingIntent).build()
        val btn = findViewById<Button>(R.id.btn)
        val notificationManager = NotificationManagerCompat.from(this)
        btn.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                notificationManager.notify(notificationId, notification)
            },5000L)
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =  NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "This is my notification channel"
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

    }
}

