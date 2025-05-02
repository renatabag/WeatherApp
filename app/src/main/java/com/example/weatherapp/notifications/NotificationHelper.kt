package com.example.weatherapp.notifications

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.Calendar
import java.util.concurrent.TimeUnit

object NotificationHelper {
    private const val WORK_TAG = "daily_weather_notification"

    fun scheduleDailyWeatherNotification(context: Context) {
        Log.d("Notification", "Scheduling work with WorkManager")

        // Устанавливаем время первого запуска (8:00 утра)
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            // Если сейчас уже позже 8 утра, планируем на следующий день
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val initialDelay = calendar.timeInMillis - System.currentTimeMillis()
        Log.d("Notification", "Initial delay: ${initialDelay / (1000 * 60 * 60)} hours")

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val notificationWork = PeriodicWorkRequestBuilder<WeatherNotificationWorker>(
            24, // Период повторения
            TimeUnit.HOURS
        )
            .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS) // Первый запуск в 8 утра
            .setConstraints(constraints)
            .addTag(WORK_TAG)
            .build()

        WorkManager.getInstance(context).apply {
            Log.d("Notification", "WorkManager instance obtained")

            enqueueUniquePeriodicWork(
                WORK_TAG,
                ExistingPeriodicWorkPolicy.REPLACE,
                notificationWork
            )

            getWorkInfosByTag(WORK_TAG).get().forEach {
                Log.d("Notification", "Work state: ${it.state}")
            }
        }
    }
}