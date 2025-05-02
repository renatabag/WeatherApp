package com.example.weatherapp.adapters


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