package com.example.weatherapp.dataBase

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.weatherapp.data.WeatherEntity

@Dao
interface WeatherDao {
    @Upsert
    suspend fun upsertLastWeather(weather: WeatherEntity)

    @Query("SELECT * FROM last_weather WHERE id = 1")
    suspend fun getLastWeather(): WeatherEntity?

    @Query("SELECT * FROM last_weather WHERE time LIKE :date || '%' LIMIT 1")
    suspend fun getWeatherForDate(date: String): WeatherEntity?


}