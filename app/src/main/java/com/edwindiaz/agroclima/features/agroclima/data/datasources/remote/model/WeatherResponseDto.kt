// features/agroclima/data/datasources/remote/model/WeatherResponseDto.kt
package com.edwindiaz.agroclima.features.agroclima.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class WeatherResponseDto(
    val coord: CoordDto,
    val weather: List<WeatherInfoDto>,
    val main: MainDto,
    val wind: WindDto,
    val clouds: CloudsDto,
    val dt: Long,
    val sys: SysDto,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)

data class CoordDto(
    val lon: Double,
    val lat: Double
)

data class WeatherInfoDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainDto(
    val temp: Double,
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    val pressure: Int,
    val humidity: Int,
    @SerializedName("sea_level") val seaLevel: Int? = null,
    @SerializedName("grnd_level") val grndLevel: Int? = null
)

data class WindDto(
    val speed: Double,
    val deg: Int,
    val gust: Double? = null
)

data class CloudsDto(
    val all: Int
)

data class SysDto(
    val country: String,
    val sunrise: Long,
    val sunset: Long
)