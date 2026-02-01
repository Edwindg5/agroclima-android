//features/agroclima/domain/entities/AlertType.kt
package com.edwindiaz.agroclima.features.agroclima.domain.entities

enum class AlertType {
    FROST,      // Heladas
    WIND,       // Vientos fuertes
    DROUGHT,    // Sequía
    RAIN,       // Lluvia intensa
    HEAT,       // Calor extremo
    OPTIMAL     // Condiciones óptimas
}

data class WeatherAlert(
    val type: AlertType,
    val message: String,
    val severity: Int // 1: baja, 2: media, 3: alta
)