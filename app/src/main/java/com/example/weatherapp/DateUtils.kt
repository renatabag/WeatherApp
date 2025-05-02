package com.example.weatherapp

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun formatDate(inputDate: String, inputPattern: String = "yyyy-MM-dd"): String {
        return try {
            val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
            val date = inputFormat.parse(inputDate) ?: return inputDate

            val outputFormat = SimpleDateFormat("dd.MM", Locale.getDefault())
            outputFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            inputDate
        }
    }

}