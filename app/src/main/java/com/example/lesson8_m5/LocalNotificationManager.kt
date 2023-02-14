package com.example.lesson8_m5

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import javax.inject.Inject

class LocalNotificationManager @Inject constructor(private val application: Application){

    init {
        createChannels()
    }

    private val builderNotification = NotificationCompat.Builder(application,Constants.CHANNEL_ID)
        .setSmallIcon(R.mipmap.ic_launcher)
    .setContentTitle("I am a title")
       .setContentText("I'm the body of notification text")
        .setStyle(NotificationCompat.BigTextStyle())
       .setPriority(NotificationCompat.PRIORITY_DEFAULT)


    @SuppressLint("MissingPermission")
    fun createNotification(){
        with(NotificationManagerCompat.from(application)){
            notify(Constants.NOTIFICATION_ID,builderNotification.build())
        }
    }

    private fun createChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           val channel = NotificationChannel(Constants.CHANNEL_ID,"Test channel",NotificationManager.IMPORTANCE_DEFAULT)

            val notificationManager: NotificationManager = application.
            getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
   notificationManager.createNotificationChannel(channel)

      }
   }
}