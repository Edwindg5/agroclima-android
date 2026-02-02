package com.edwindiaz.agroclima.features.agroclima.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.edwindiaz.agroclima.features.agroclima.data.datasources.local.ChiapasLocations
import com.edwindiaz.agroclima.features.agroclima.domain.entities.Forecast
import com.edwindiaz.agroclima.features.agroclima.domain.repositories.WeatherRepository
import com.edwindiaz.agroclima.features.agroclima.domain.usecases.GetForecastUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// 4. Modificar ForecastViewModel.kt
class ForecastViewModel(
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _forecasts = MutableStateFlow<List<Forecast>>(emptyList())
    val forecasts = _forecasts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    fun loadForecast() {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            val location = ChiapasLocations.locations.first()
            val result = getForecastUseCase(location)

            result.fold(
                onSuccess = { forecasts ->
                    _forecasts.value = forecasts
                    _isLoading.value = false
                },
                onFailure = { error ->
                    _error.value = error.message ?: "Error al cargar pronóstico"
                    _isLoading.value = false
                }
            )
        }
    }
}