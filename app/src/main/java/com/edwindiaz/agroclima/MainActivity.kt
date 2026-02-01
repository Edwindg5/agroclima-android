package com.edwindiaz.agroclima

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.edwindiaz.agroclima.core.di.AppContainer
import com.edwindiaz.agroclima.features.agroclima.di.WeatherModule
import com.edwindiaz.agroclima.features.agroclima.presentation.navigation.AppNavigation
import com.edwindiaz.agroclima.ui.theme.AgroClimaTheme

class MainActivity : ComponentActivity() {

    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(applicationContext)

        setContent {
            AgroClimaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val weatherModule = WeatherModule(appContainer)
                    val factory = weatherModule.provideWeatherViewModelFactory()

                    AppNavigation(factory = factory)
                }
            }
        }
    }
}