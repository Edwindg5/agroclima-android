//features/agroclima/data/datasources/remote/mapper/ForecastMapper.kt
package com.edwindiaz.agroclima.features.agroclima.data.datasources.remote.mapper

import com.edwindiaz.agroclima.features.agroclima.data.datasources.remote.model.ForecastResponseDto
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Forecast
import java.text.SimpleDateFormat
import java.util.*

fun ForecastResponseDto.toDomain(): List<Forecast> {
    // Agrupar por día
    val forecasts = mutableListOf<Forecast>()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    // Tomar solo un forecast por día (el del mediodía)
    val groupedByDay = this.list
        .filter { it.dtTxt.contains("12:00:00") }
        .take(5) // Solo 5 días

    groupedByDay.forEach { item ->
        val date = try {
            val parts = item.dtTxt.split(" ")[0].split("-")
            "${parts[2]}/${parts[1]}" // DD/MM
        } catch (e: Exception) {
            "N/A"
        }

        forecasts.add(
            Forecast(
                date = date,
                tempMin = item.main.tempMin,
                tempMax = item.main.tempMax,
                description = item.weather.firstOrNull()?.description ?: "Despejado",
                icon = item.weather.firstOrNull()?.icon ?: "01d",
                humidity = item.main.humidity
            )
        )
    }

    return forecasts
}