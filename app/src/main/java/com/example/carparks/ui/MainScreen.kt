package com.example.carparks.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.carparks.R

@Composable
fun MainScreen(navController: NavController) {
    val infoText = stringResource(R.string.info)
    val settingsText = stringResource(R.string.settings)

    Scaffold(
        topBar = { MainTopBar(title = stringResource(R.string.title), infoText = infoText, settingsText = settingsText, navController = navController) },
        content = { innerPadding -> ParksScreen(modifier = Modifier.padding(innerPadding))},
    )
}
