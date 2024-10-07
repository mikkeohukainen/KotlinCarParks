package com.example.carparks.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carparks.R

@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(stringResource(R.string.info), navController) },
        content = { innerPadding ->
            InfoContent(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize())
        }
    )
}

@Composable
fun InfoContent(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        shape = MaterialTheme.shapes.medium,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = stringResource(R.string.about),
                style = MaterialTheme.typography.titleLarge
            )

            Text(
                text = stringResource(R.string.about2),
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = stringResource(R.string.about3),
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = stringResource(R.string.about4),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
            )

            Text(
                text = stringResource(R.string.about5),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
