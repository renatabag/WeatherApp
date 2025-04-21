package com.example.weatherapp.adapters

import java.util.concurrent.locks.Condition

data class WeatherModel(
    val city: String,
    val time: String,
    val condition: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemap: String,
    val imageUrl: String,
    val hours: String
)