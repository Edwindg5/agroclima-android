package com.edwindiaz.agroclima.features.agroclima.domain.entities

data class Forecast(
    val date: String,
    val tempMin: Double,
    val tempMax: Double,
    val description: String,
    val icon: String,
    val humidity: Int
)