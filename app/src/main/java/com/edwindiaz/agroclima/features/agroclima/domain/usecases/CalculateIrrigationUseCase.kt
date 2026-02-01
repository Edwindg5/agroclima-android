//features/agroclima/domain/usecases/CalculateIrrigationUseCase.kt
package com.edwindiaz.agroclima.features.agroclima.domain.usecases

import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather

class CalculateIrrigationUseCase {

    data class IrrigationRecommendation(
        val shouldIrrigate: Boolean,
        val recommendation: String,
        val estimatedMinutes: Int
    )

    operator fun invoke(
        weather: Weather,
        lastIrrigationDays: Int = 0,
        soilHumidity: Int = 50
    ): IrrigationRecommendation {

        val tempFactor = when {
            weather.temperature > 30 -> 1.5  // Más riego si hace calor
            weather.temperature > 20 -> 1.0
            else -> 0.7  // Menos riego si hace frío
        }

        val humidityFactor = when {
            weather.humidity > 80 -> 0.5  // Menos riego si hay mucha humedad
            weather.humidity > 60 -> 0.8
            weather.humidity < 30 -> 1.5  // Más riego si está seco
            else -> 1.0
        }

        val soilFactor = when {
            soilHumidity < 30 -> 1.5
            soilHumidity < 50 -> 1.2
            else -> 0.8
        }

        val daysFactor = when {
            lastIrrigationDays > 5 -> 1.5
            lastIrrigationDays > 3 -> 1.2
            else -> 1.0
        }

        // Calcular si debe regar
        val shouldIrrigate = when {
            weather.temperature < 5 -> false  // No regar si hace mucho frío
            weather.humidity > 90 -> false    // No regar si está muy húmedo
            lastIrrigationDays < 1 -> false   // No regar si regó ayer
            else -> true
        }

        // Calcular minutos estimados
        val baseMinutes = 30
        val estimatedMinutes = (baseMinutes * tempFactor * humidityFactor * soilFactor * daysFactor).toInt()

        val recommendation = when {
            !shouldIrrigate -> "No es necesario regar hoy"
            estimatedMinutes < 20 -> "Riegue por $estimatedMinutes minutos"
            estimatedMinutes < 40 -> "Riegue por $estimatedMinutes minutos (tiempo normal)"
            else -> "Riegue por $estimatedMinutes minutos (tiempo extendido)"
        }

        return IrrigationRecommendation(
            shouldIrrigate = shouldIrrigate,
            recommendation = recommendation,
            estimatedMinutes = estimatedMinutes
        )
    }
}