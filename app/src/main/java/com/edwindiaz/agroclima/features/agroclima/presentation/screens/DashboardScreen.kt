//features/agroclima/presentation/screens/DashboardScreen.kt

package com.edwindiaz.agroclima.features.agroclima.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edwindiaz.agroclima.features.agroclima.data.datasources.local.ChiapasLocations
import com.edwindiaz.agroclima.features.agroclima.presentation.components.AlertCard
import com.edwindiaz.agroclima.features.agroclima.presentation.components.IrrigationCalculatorCard
import com.edwindiaz.agroclima.features.agroclima.presentation.components.WeatherCard
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModel
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModelFactory


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: WeatherViewModel,
    onNavigateToForecast: () -> Unit,
    onNavigateToLocations: () -> Unit,
    onNavigateToIrrigation: () -> Unit
) {



    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (uiState.selectedLocation == null) {
            viewModel.selectLocation(ChiapasLocations.locations.first())
        } else if (uiState.weather == null && !uiState.isLoading) {
            viewModel.loadWeather()
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🌱 AgroClima") }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                onDashboard = { /* Ya estamos aquí */ },
                onForecast = onNavigateToForecast,
                onLocations = onNavigateToLocations,
                onIrrigation = onNavigateToIrrigation
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                uiState.error != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = uiState.error ?: "Error",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(onClick = { viewModel.refresh() }) {
                            Text("Reintentar")
                        }
                    }
                }
                uiState.weather != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        item {
                            WeatherCard(weather = uiState.weather!!)
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        if (uiState.alerts.isNotEmpty()) {
                            item {
                                Text(
                                    text = "Alertas Agrícolas",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                )
                            }

                            items(uiState.alerts) { alert ->
                                AlertCard(alert = alert)
                            }
                        }

                        item {
                            Spacer(modifier = Modifier.height(8.dp))
                        }

                        uiState.irrigationRecommendation?.let { recommendation ->
                            item {
                                IrrigationCalculatorCard(
                                    recommendation = recommendation,
                                    onNavigateToIrrigation = onNavigateToIrrigation
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    onDashboard: () -> Unit,
    onForecast: () -> Unit,
    onLocations: () -> Unit,
    onIrrigation: () -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBarItem(
            icon = {
                Text(text = "🏠", style = MaterialTheme.typography.headlineSmall)
            },
            label = { Text("Inicio") },
            selected = true,
            onClick = onDashboard
        )

        NavigationBarItem(
            icon = {
                Text(text = "📅", style = MaterialTheme.typography.headlineSmall)
            },
            label = { Text("Pronóstico") },
            selected = false,
            onClick = onForecast
        )

        NavigationBarItem(
            icon = {
                Text(text = "📍", style = MaterialTheme.typography.headlineSmall)
            },
            label = { Text("Ubicaciones") },
            selected = false,
            onClick = onLocations
        )

        NavigationBarItem(
            icon = {
                Text(text = "💧", style = MaterialTheme.typography.headlineSmall)
            },
            label = { Text("Riego") },
            selected = false,
            onClick = onIrrigation
        )
    }
}