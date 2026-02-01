package com.edwindiaz.agroclima.features.agroclima.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.edwindiaz.agroclima.features.agroclima.domain.entities.ChiapasLocations
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Location
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModel
import com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels.WeatherViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(
    factory: WeatherViewModelFactory,
    onNavigateBack: () -> Unit
) {
    val viewModel: WeatherViewModel = viewModel(factory = factory)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("📍 Ubicaciones de Chiapas") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Text(text = "←", style = MaterialTheme.typography.headlineMedium)
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            item {
                Text(
                    text = "Selecciona una ubicación",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(ChiapasLocations.locations) { location ->
                LocationCard(
                    location = location,
                    onClick = {
                        viewModel.selectLocation(location)
                        onNavigateBack()
                    }
                )
            }
        }
    }
}

@Composable
fun LocationCard(
    location: Location,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onClick() },
        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = location.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Lat: ${location.latitude}, Lon: ${location.longitude}",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}