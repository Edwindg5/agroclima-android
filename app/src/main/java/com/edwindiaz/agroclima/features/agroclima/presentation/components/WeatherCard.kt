//features/agroclima/presentation/components/WeatherCard.kt
package com.edwindiaz.agroclima.features.agroclima.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Weather

@Composable
fun WeatherCard(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Encabezado con ubicación
            Text(
                text = weather.location.name,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Temperatura principal
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icono del clima
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${weather.weatherIcon}@2x.png",
                    contentDescription = weather.weatherDescription,
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "${weather.temperature.toInt()}°C",
                        style = MaterialTheme.typography.displayMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Sensación: ${weather.feelsLike.toInt()}°C",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Detalles del clima en 2 filas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherDetailItem(
                    emoji = "💧",
                    title = "Humedad",
                    value = "${weather.humidity}%"
                )

                WeatherDetailItem(
                    emoji = "💨",
                    title = "Viento",
                    value = "${weather.windSpeed.toInt()} km/h"
                )

                WeatherDetailItem(
                    emoji = "📊",
                    title = "Presión",
                    value = "${weather.pressure} hPa"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherDetailItem(
                    emoji = "❄️",
                    title = "Mínima",
                    value = "${weather.minTemperature.toInt()}°C"
                )

                WeatherDetailItem(
                    emoji = "🌡️",
                    title = "Máxima",
                    value = "${weather.maxTemperature.toInt()}°C"
                )

                WeatherDetailItem(
                    emoji = "🌤️",
                    title = "Condición",
                    value = weather.weatherDescription
                )
            }
        }
    }
}

@Composable
fun WeatherDetailItem(
    emoji: String,
    title: String,
    value: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = emoji,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}