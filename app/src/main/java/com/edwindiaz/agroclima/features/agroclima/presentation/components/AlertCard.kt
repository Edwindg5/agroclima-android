//features/agroclima/presentation/components/AlertCard.kt
package com.edwindiaz.agroclima.features.agroclima.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.edwindiaz.agroclima.features.agroclima.domain.entities.AlertType
import com.edwindiaz.agroclima.features.agroclima.domain.entities.WeatherAlert

@Composable
fun AlertCard(
    alert: WeatherAlert,
    modifier: Modifier = Modifier
) {
    val (backgroundColor, iconColor, icon) = when (alert.type) {
        AlertType.FROST -> Triple(
            Color(0xFFE3F2FD), // Azul claro
            Color(0xFF2196F3), // Azul
            painterResource(android.R.drawable.ic_menu_compass)
        )
        AlertType.WIND -> Triple(
            Color(0xFFF3E5F5), // Lila claro
            Color(0xFF9C27B0), // Lila
            painterResource(android.R.drawable.ic_lock_idle_alarm)
        )
        AlertType.OPTIMAL -> Triple(
            Color(0xFFE8F5E8), // Verde claro
            Color(0xFF4CAF50), // Verde
            painterResource(android.R.drawable.ic_menu_save)
        )
        else -> Triple(
            Color(0xFFFFF3E0), // Naranja claro
            Color(0xFFFF9800), // Naranja
            painterResource(android.R.drawable.ic_dialog_alert)
        )
    }

    val severityText = when (alert.severity) {
        1 -> "BAJA"
        2 -> "MEDIA"
        3 -> "ALTA"
        else -> ""
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = icon,
                contentDescription = alert.type.name,
                modifier = Modifier.size(32.dp),
                tint = iconColor
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = when (alert.type) {
                            AlertType.FROST -> "⚠️ Alerta de Heladas"
                            AlertType.WIND -> "💨 Alerta de Vientos"
                            AlertType.OPTIMAL -> "✅ Condiciones Óptimas"
                            else -> "⚠️ Alerta Climática"
                        },
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = iconColor
                    )

                    Text(
                        text = severityText,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = iconColor
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = alert.message,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}