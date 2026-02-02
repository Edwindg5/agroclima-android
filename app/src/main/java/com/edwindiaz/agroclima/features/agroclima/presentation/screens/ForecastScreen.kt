//features/agroclima/presentation/screens/ForecastScreen.kt
package com.edwindiaz.agroclima.features.agroclima.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Forecast
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.ForecastViewModel
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen(
    factory: WeatherViewModelFactory,
    onNavigateBack: () -> Unit
) {
    val viewModel: ForecastViewModel = viewModel(factory = factory)
    val forecasts by viewModel.forecasts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadForecast()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📅 Pronóstico 5 Días") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text(text = "←", style = MaterialTheme.typography.headlineMedium)
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                error != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = error ?: "Error",
                            color = Color.Red,
                            modifier = Modifier.padding(16.dp)
                        )
                        Button(onClick = { viewModel.loadForecast() }) {
                            Text("Reintentar")
                        }
                    }
                }
                forecasts.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(forecasts) { forecast ->
                            ForecastDayCard(forecast = forecast)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ForecastDayCard(forecast: Forecast) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Fecha
            Text(
                text = forecast.date,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            // Ícono del clima
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${forecast.icon}.png",
                contentDescription = forecast.description,
                modifier = Modifier.size(40.dp)
            )

            // Temperaturas
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${forecast.tempMax.toInt()}° / ${forecast.tempMin.toInt()}°",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = forecast.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}