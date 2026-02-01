//features/agroclima/domain/entities/Weather.kt
package com.edwindiaz.agroclima.features.agroclima.domain.entities

data class Weather(
    val location: Location,
    val temperature: Double,
    val feelsLike: Double,
    val minTemperature: Double,
    val maxTemperature: Double,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val weatherCondition: String,
    val weatherDescription: String,
    val weatherIcon: String,
    val sunrise: Long,
    val sunset: Long,
    val timestamp: Long
)