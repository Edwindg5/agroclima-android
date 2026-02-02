//features/agroclima/presentation/navigation/AppNavigation.kt
package com.edwindiaz.agroclima.features.agroclima.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.DashboardScreen
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.ForecastScreen
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.IrrigationScreen
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.LocationsScreen
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModel
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModelFactory

@Composable
fun AppNavigation(
    factory: WeatherViewModelFactory,
    navController: NavHostController = rememberNavController()
) {

    val weatherViewModel: WeatherViewModel = remember {
        factory.create(WeatherViewModel::class.java)
    }

    NavHost(
        navController = navController,
        startDestination = AppScreens.Dashboard.route
    ) {
        composable(AppScreens.Dashboard.route) {
            DashboardScreen(
                viewModel = weatherViewModel,
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
                viewModel = weatherViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(AppScreens.Irrigation.route) {
            IrrigationScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}