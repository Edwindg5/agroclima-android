//features/agroclima/presentation/navigation/AppScrrens
package com.edwindiaz.agroclima.features.agroclima.presentation.navigation

sealed class AppScreens(val route: String) {
    object Dashboard : AppScreens("dashboard")
    object Forecast : AppScreens("forecast")
    object Locations : AppScreens("locations")
    object Irrigation : AppScreens("irrigation")

    // Para pasar parámetros
    object WeatherDetail : AppScreens("weather_detail/{locationName}") {
        fun createRoute(locationName: String) = "weather_detail/$locationName"
    }
}