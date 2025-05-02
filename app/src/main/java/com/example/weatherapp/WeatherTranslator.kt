package com.example.weatherapp

import android.util.Log

object WeatherTranslator {
    private val translations = mapOf(
        // Основные погодные условия
        "Sunny" to "Солнечно",
        "Clear" to "Ясно",
        "Partly Сloudy" to "Переменная облачность",
        "Cloudy" to "Облачно",
        "Overcast" to "Пасмурно",
        "Mist" to "Дымка",
        "Fog" to "Туман",
        "Freezing fog" to "Ледяной туман",

        // Осадки и осадки с возможностью
        "Patchy rain possible" to "Возможен небольшой дождь",
        "Patchy snow possible" to "Возможен небольшой снег",
        "Patchy sleet possible" to "Возможен мокрый снег",
        "Patchy freezing drizzle possible" to "Возможен ледяной дождь",
        "Thundery outbreaks possible" to "Возможны грозы",

        // Дождь
        "Light rain" to "Небольшой дождь",
        "Moderate rain" to "Умеренный дождь",
        "Heavy rain" to "Сильный дождь",
        "Light freezing rain" to "Небольшой ледяной дождь",
        "Moderate or heavy freezing rain" to "Умеренный или сильный ледяной дождь",

        // Снег и метели
        "Light snow" to "Небольшой снег",
        "Moderate snow" to "Умеренный снег",
        "Heavy snow" to "Сильный снег",
        "Patchy heavy snow" to "Местами сильный снег",
        "Blowing snow" to "Метель",
        "Blizzard" to "Снежная буря",
        "Ice pellets" to "Ледяная крупа",

        // Грозы
        "Thunderstorm" to "Гроза",
        "Moderate or heavy rain with thunder" to "Умеренный или сильный дождь с грозой",
        "Moderate or heavy snow with thunder" to "Умеренный или сильный снег с грозой",

        // Ветер
        "Windy" to "Ветрено",
        "Calm" to "Штиль",
        "Breeze" to "Легкий ветер",

        // Температурные условия
        "Freezing" to "Мороз",
        "Hot" to "Жара",

        // Особые явления
        "Showers" to "Ливень",
        "Torrential rain shower" to "Проливной дождь",
        "Patchy rain nearby" to "Местами дождь"
    )

    fun translate(term: String): String {
        return translations[term] ?: term.also {
            Log.w("WeatherTranslator", "No translation for: $term")
        }
    }
}