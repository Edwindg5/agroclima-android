//features/agroclima/domain/usecases/CheckWindAlertUseCase.kt
package com.edwindiaz.agroclima.features.agroclima.domain.usecases

import com.edwindiaz.agroclima.features.agroclima.domain.entities.AlertType
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather
import com.edwindiaz.agroclima.features.agroclima.domain.entities.WeatherAlert

class CheckWindAlertUseCase {
    operator fun invoke(weather: Weather): WeatherAlert? {
        return when {
            weather.windSpeed > 30 -> WeatherAlert(
                type = AlertType.WIND,
                message = "¡Vientos fuertes! No recomendado para aspersión",
                severity = 3
            )
            weather.windSpeed > 20 -> WeatherAlert(
                type = AlertType.WIND,
                message = "Vientos moderados, evite pulverizar pesticidas",
                severity = 2
            )
            else -> null
        }
    }
}