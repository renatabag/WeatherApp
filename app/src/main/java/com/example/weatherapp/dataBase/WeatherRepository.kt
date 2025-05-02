package com.example.weatherapp.dataBase

import com.example.weatherapp.adapters.WeatherModel
import com.example.weatherapp.data.toEntity
import com.example.weatherapp.data.toModel

class WeatherRepository(private val weatherDao: WeatherDao) {

    suspend fun saveLastWeather(weather: WeatherModel) {
        weatherDao.upsertLastWeather(weather.toEntity())
    }

    suspend fun getLastWeather(): WeatherModel? {
        return weatherDao.getLastWeather()?.toModel()
    }
}