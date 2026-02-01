//features/agroclima/data/datasources/remote/mapper/WeatherMapper.kt
package com.edwindiaz.agroclima.features.agroclima.data.datasources.remote.mapper

import com.edwindiaz.agroclima.features.agroclima.data.datasources.remote.model.WeatherResponseDto
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Location

fun WeatherResponseDto.toDomain(): Weather {
    return Weather(
        location = Location(
            name = this.name,
            latitude = this.coord.lat,
            longitude = this.coord.lon
        ),
        temperature = this.main.temp,
        feelsLike = this.main.feelsLike,        // Cambio aquí
        minTemperature = this.main.tempMin,     // Cambio aquí
        maxTemperature = this.main.tempMax,     // Cambio aquí
        humidity = this.main.humidity,
        pressure = this.main.pressure,
        windSpeed = this.wind.speed,
        windDirection = this.wind.deg,
        weatherCondition = this.weather.firstOrNull()?.main ?: "Despejado",
        weatherDescription = this.weather.firstOrNull()?.description ?: "",
        weatherIcon = this.weather.firstOrNull()?.icon ?: "01d",
        sunrise = this.sys.sunrise,
        sunset = this.sys.sunset,
        timestamp = this.dt
    )
}