package com.example.weatherapp.dataBase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.adapters.WeatherModel
import com.example.weatherapp.data.WeatherDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val liveDataCurrent = MutableLiveData<WeatherModel>()
    val liveDataList = MutableLiveData<List<WeatherModel>>()

    private val repository: WeatherRepository

    init {
        val weatherDao = WeatherDatabase.Companion.getDatabase(application).weatherDao()
        repository = WeatherRepository(weatherDao)
    }

    fun saveLastWeather(weather: WeatherModel) {
        viewModelScope.launch {
            repository.saveLastWeather(weather)
        }
    }

    fun loadLastWeather() {
        viewModelScope.launch {
            val lastWeather = repository.getLastWeather()
            lastWeather?.let {
                liveDataCurrent.postValue(it)
            }
        }
    }


}