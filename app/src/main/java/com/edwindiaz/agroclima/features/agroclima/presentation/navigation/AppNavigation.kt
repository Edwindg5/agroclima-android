//features/agroclima/presentation/navigation/AppNavigation.kt
package com.edwindiaz.agroclima.features.agroclima.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.DashboardScreen
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.ForecastScreen
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.IrrigationScreen
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.LocationsScreen
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModelFactory

@Composable
fun AppNavigation(
    factory: WeatherViewModelFactory,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        navController = navController,
        startDestination = AppScreens.Dashboard.route
    ) {
        composable(AppScreens.Dashboard.route) {
            DashboardScreen(
                factory = factory,
                onNavigateToForecast = {
                    navController.navigate(AppScreens.Forecast.route)
                },
                onNavigateToLocations = {
                    navController.navigate(AppScreens.Locations.route)
                },
                onNavigateToIrrigation = {
                    navController.navigate(AppScreens.Irrigation.route)
                }
            )
        }

        composable(AppScreens.Forecast.route) {
            ForecastScreen(
                factory = factory,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(AppScreens.Locations.route) {
            LocationsScreen(
                factory = factory,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(AppScreens.Irrigation.route) {
            IrrigationScreen(
                factory = factory,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}