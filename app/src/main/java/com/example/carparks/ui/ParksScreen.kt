package com.example.carparks.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.carparks.viewmodel.ParksViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carparks.R

@Composable
fun ParksScreen(viewModel: ParksViewModel = viewModel(), modifier: Modifier = Modifier) {

    val parks by remember { viewModel.parks }
    val loading by remember { viewModel.loading }
    val error by remember { viewModel.error }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when {
            loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(16.dp)
                    )
                }
            }
            error != null -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.API_error), style = MaterialTheme.typography.bodyLarge)
                }
            }
            parks.isNotEmpty() -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(parks) { park ->
                        CarParkItem(park)
                    }
                }
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(R.string.no_car_parks_available), style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
