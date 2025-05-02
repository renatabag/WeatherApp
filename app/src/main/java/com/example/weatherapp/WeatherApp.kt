package com.example.weatherapp

import android.app.Application
import android.util.Log
import androidx.work.WorkManager
import com.example.weatherapp.notifications.NotificationHelper

class WeatherApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("Notification", "Application started, package: ${packageName}")

        NotificationHelper.scheduleDailyWeatherNotification(this)

        WorkManager.getInstance(this).getWorkInfosByTag("daily_weather_notification").get().forEach {
            Log.d("Notification", "Existing work: ${it.id}, state: ${it.state}")
        }
    }

}