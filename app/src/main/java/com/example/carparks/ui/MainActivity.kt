package com.example.carparks.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carparks.ui.theme.CarParksTheme
import com.example.carparks.viewmodel.UiViewModel
import java.util.Locale
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val uiViewModel: UiViewModel = viewModel()

            CompositionLocalProvider(LocalContext provides updateLocale(this, uiViewModel.locale.value)) {

                CarParksTheme(darkTheme = uiViewModel.isDarkTheme.value) {

                    CarParksApp(uiViewModel = uiViewModel)
                }
            }
        }
    }
    private fun updateLocale(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}


@Composable
fun CarParksApp(uiViewModel: UiViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") { MainScreen(navController) }
        composable("info") { InfoScreen(navController) }
        composable("settings") {
            SettingsScreen(
                navController = navController,
                isDarkTheme = uiViewModel.isDarkTheme.value,
                onThemeChange = { uiViewModel.toggleTheme(it) },
                currentLocale = uiViewModel.locale.value,
                onLanguageChange = { uiViewModel.switchLanguage(it) }
            )
        }
    }
}
