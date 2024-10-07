package com.example.carparks.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.util.Locale

class UiViewModel : ViewModel() {
    var isDarkTheme = mutableStateOf(false)
        private set

    var locale = mutableStateOf(Locale.getDefault())
        private set

    fun toggleTheme(isDark: Boolean) {
        isDarkTheme.value = isDark
    }

    fun switchLanguage(newLocale: Locale) {
        locale.value = newLocale
    }
}