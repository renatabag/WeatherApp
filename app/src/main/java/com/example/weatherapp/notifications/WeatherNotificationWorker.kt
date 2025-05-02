package com.example.weatherapp.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.weatherapp.R
import com.example.weatherapp.WeatherTranslator
import com.example.weatherapp.data.WeatherDatabase
import com.example.weatherapp.data.WeatherEntity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class WeatherNotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Log.d("Notification", "Worker started at ${System.currentTimeMillis()}")

        return try {
            val db = WeatherDatabase.Companion.getDatabase(applicationContext)
            val allData = db.weatherDao().getLastWeather()
            Log.d("Notification", "All data in DB: $allData")

            val tomorrowWeather = getTomorrowWeather()
            Log.d("Notification", "Today weather: $tomorrowWeather")

            tomorrowWeather?.let {
                Log.d("Notification", "Showing notification")
                showWeatherNotification(it)
            } ?: Log.d("Notification", "No weather data available")

            Result.success()
        } catch (e: Exception) {
            Log.e("Notification", "Worker error", e)
            Result.failure()
        }
    }

    private suspend fun getTomorrowWeather(): WeatherEntity? {
        val database = WeatherDatabase.Companion.getDatabase(applicationContext)
        return database.weatherDao().getWeatherForDate(getTomorrowDate()) ?: run {
            database.weatherDao().getLastWeather()
        }
    }

    private fun showWeatherNotification(weather: WeatherEntity) {
        createNotificationChannel()

        val notification = NotificationCompat.Builder(applicationContext, "weather_channel")
            .setContentTitle("Сегодня в г.${weather.city}")
            .setContentText( "${WeatherTranslator.translate(weather.condition)}, ${weather.maxTemp}°C / ${weather.minTemp}°C")
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val notificationManager = applicationContext.getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "weather_channel",
                "Прогноз погоды",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Ежедневный прогноз погоды"
                enableLights(true)
                lightColor = Color.RED
            }
            val manager = applicationContext.getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }

    companion object {
        const val NOTIFICATION_ID = 1
        fun getTomorrowDate(): String {
            return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                .format(Calendar.getInstance().apply {
                    add(Calendar.DAY_OF_YEAR, 1)
                }.time)
        }
    }
}