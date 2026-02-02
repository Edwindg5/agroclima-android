//features/agroclima/data/repositories/WeatherRepositoryImpl.kt
package com.edwindiaz.agroclima.features.agroclima.data.repositories

import com.edwindiaz.agroclima.core.network.WeatherApi
import com.edwindiaz.agroclima.features.agroclima.data.datasources.remote.mapper.toDomain
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Forecast
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Location
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather
import com.edwindiaz.agroclima.features.agroclima.domain.repositories.WeatherRepository


class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val apiKey: String
) : WeatherRepository {

    override suspend fun getCurrentWeather(location: Location): Weather {
        val response = api.getCurrentWeather(
            lat = location.latitude,
            lon = location.longitude,
            apiKey = apiKey
        )
        return response.toDomain()
    }

    override suspend fun getForecast(location: Location): List<Forecast> {
        val response = api.getForecast(
            lat = location.latitude,
            lon = location.longitude,
            apiKey = apiKey
        )
        return response.toDomain()
    }
}