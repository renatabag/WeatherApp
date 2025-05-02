package com.example.weatherapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.weatherapp.adapters.WeatherModel

@Entity(tableName = "last_weather")
data class WeatherEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 1,
    val city: String,
    val time: String,
    val condition: String,
    val currentTemp: String,
    val maxTemp: String,
    val minTemp: String,
    val imageUrl: String,
    val hours: String
)

fun WeatherModel.toEntity(): WeatherEntity {
    return WeatherEntity(
        city = this.city,
        time = this.time,
        condition = this.condition,
        currentTemp = this.currentTemp,
        maxTemp = this.maxTemp,
        minTemp = this.minTemap,
        imageUrl = this.imageUrl,
        hours = this.hours
    )
}

fun WeatherEntity.toModel(): WeatherModel {
    return WeatherModel(
        city = this.city,
        time = this.time,
        condition = this.condition,
        currentTemp = this.currentTemp,
        maxTemp = this.maxTemp,
        minTemap = this.minTemp,
        imageUrl = this.imageUrl,
        hours = this.hours
    )
}