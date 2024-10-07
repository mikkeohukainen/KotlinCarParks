package com.example.carparks.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.carparks.R
import com.example.carparks.model.CarPark

@Composable
fun CarParkItem(park: CarPark) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = park.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(
                    R.string.available_spaces,
                    park.spacesAvailable ?: stringResource(R.string.n_a)
                ),
                style = MaterialTheme.typography.bodyMedium
            )

            park.maxCapacity?.let { maxCapacity ->
                Text(
                    text = stringResource(R.string.max_capacity, maxCapacity),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            park.pricing?.let { pricingList ->
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                pricingList.forEach { pricing ->
                    Text(
                        text = "${pricing.title["fi"]}: ${pricing.value["fi"]}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

