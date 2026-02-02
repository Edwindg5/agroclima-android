//features/agroclima/data/datasources/remote/model/WeatherResponseDto.kt
package com.edwindiaz.agroclima.features.agroclima.data.datasources.remote.model

import com.google.gson.annotations.SerializedName

data class ForecastResponseDto(
    val list: List<ForecastItemDto>,
    val city: CityDto
)

data class ForecastItemDto(
    val dt: Long,
    @SerializedName("main") val main: MainDto,
    @SerializedName("weather") val weather: List<WeatherInfoDto>,
    @SerializedName("dt_txt") val dtTxt: String
)

data class CityDto(
    val name: String,
    val coord: CoordDto
)