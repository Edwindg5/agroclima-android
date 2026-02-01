//features/agroclima/presentation/screens/ForecastScreen.kt
package com.edwindiaz.agroclima.features.agroclima.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edwindiaz.agroclima.features.agroclima.presentation.components.WeatherCard
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModel
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForecastScreen(
    factory: WeatherViewModelFactory,
    onNavigateBack: () -> Unit
) {
    val viewModel: WeatherViewModel = viewModel(factory = factory)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📅 Pronóstico 5 Días") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = onNavigateBack) {
                        androidx.compose.material3.Icon(
                            painter = androidx.compose.ui.res.painterResource(android.R.drawable.ic_menu_revert),
                            contentDescription = "Volver"
                        )
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
                            Text(
                                text = "Pronóstico para ${uiState.weather!!.location.name}",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(16.dp)
                            )
                        }

                        // Aquí irían los días del pronóstico
                        items(listOf("Hoy", "Mañana", "Miércoles", "Jueves", "Viernes")) { day ->
                            ForecastDayCard(day = day, weather = uiState.weather!!)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ForecastDayCard(day: String, weather: com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather) {
    androidx.compose.material3.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = androidx.compose.ui.Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = day,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "18°-26°C | 30% lluvia",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = "✅ Día óptimo",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Green
            )
        }
    }
}