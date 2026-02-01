//features/agroclima/domain/usecases/CheckFrostAlertUseCase.kt
package com.edwindiaz.agroclima.features.agroclima.domain.usecases

import com.edwindiaz.agroclima.features.agroclima.domain.entities.AlertType
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather
import com.edwindiaz.agroclima.features.agroclima.domain.entities.WeatherAlert

class CheckFrostAlertUseCase {
    operator fun invoke(weather: Weather): WeatherAlert? {
        return when {
            weather.temperature < 3 -> WeatherAlert(
                type = AlertType.FROST,
                message = "¡Alerta de heladas! Temperatura muy baja para cultivos sensibles",
                severity = 3
            )
            weather.temperature < 8 -> WeatherAlert(
                type = AlertType.FROST,
                message = "Temperatura baja, proteja cultivos sensibles",
                severity = 2
            )
            else -> null
        }
    }
}