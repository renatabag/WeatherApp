package com.example.weatherapp

import java.net.URL
import java.util.concurrent.locks.Condition

data class DayItem(
    val city : String,
    val time : String,
    val condition: String,
    val imageURL : String,
    val currentTemp : String,
    val maxTemp : String,
    val minTemp : String,
    val hours : String
)
