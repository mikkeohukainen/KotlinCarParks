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
    Scaffold(
        topBar = { MainTopBar(stringResource(R.string.title), navController) },
        content = { innerPadding -> ParksScreen(modifier = Modifier.padding(innerPadding))},
    )
}
