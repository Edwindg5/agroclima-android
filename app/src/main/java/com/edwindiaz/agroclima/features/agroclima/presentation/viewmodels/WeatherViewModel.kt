package com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Location
import com.edwindiaz.agroclima.features.agroclima.domain.entities.WeatherAlert
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CalculateIrrigationUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CheckFrostAlertUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.CheckWindAlertUseCase
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.GetCurrentWeatherUseCase
import com.edwindiaz.agroclima.features.agroclima.presentation.screens.uiState.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val checkFrostAlertUseCase: CheckFrostAlertUseCase,
    private val checkWindAlertUseCase: CheckWindAlertUseCase,
    private val calculateIrrigationUseCase: CalculateIrrigationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(WeatherUiState())
    val uiState = _uiState.asStateFlow()

    // NUEVO: Función para cambiar de ubicación
    fun selectLocation(location: Location) {
        _uiState.update { it.copy(selectedLocation = location) }
        loadWeather(location)
    }

    // MODIFICADO: Ahora recibe la ubicación como parámetro
    fun loadWeather(location: Location? = null) {
        val targetLocation = location ?: _uiState.value.selectedLocation ?: return

        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                val result = getCurrentWeatherUseCase(targetLocation)

                _uiState.update { currentState ->
                    result.fold(
                        onSuccess = { weather ->
                            val alerts = mutableListOf<WeatherAlert>()

                            checkFrostAlertUseCase(weather)?.let { alerts.add(it) }
                            checkWindAlertUseCase(weather)?.let { alerts.add(it) }

                            if (alerts.isEmpty() &&
                                weather.temperature in 15.0..25.0 &&
                                weather.humidity in 60..80) {
                                alerts.add(
                                    WeatherAlert(
                                        type = com.edwindiaz.agroclima.features.agroclima.domain.entities.AlertType.OPTIMAL,
                                        message = "Condiciones óptimas para la mayoría de cultivos",
                                        severity = 1
                                    )
                                )
                            }

                            val irrigation = calculateIrrigationUseCase(
                                weather = weather,
                                lastIrrigationDays = 2,
                                soilHumidity = 65
                            )

                            currentState.copy(
                                isLoading = false,
                                weather = weather,
                                alerts = alerts,
                                irrigationRecommendation = irrigation
                            )
                        },
                        onFailure = { error ->
                            currentState.copy(
                                isLoading = false,
                                error = error.message ?: "Error desconocido"
                            )
                        }
                    )
                }
            } catch (e: Exception) {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        error = e.message ?: "Error al cargar el clima"
                    )
                }
            }
        }
    }

    fun refresh() {
        loadWeather()
    }
}