package com.example.carparks.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.carparks.R
import com.example.carparks.model.CarPark

@Composable
fun CarParkItem(park: CarPark) {
    val context = LocalContext.current
    val locale = context.resources.configuration.locales[0].language

    val languageCode = when (locale) {
        "fi" -> "fi"
        "en" -> "en"
        else -> "en"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = park.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(
                    R.string.available_spaces,
                    park.spacesAvailable ?: stringResource(R.string.n_a)
                ),
                style = MaterialTheme.typography.bodyLarge
            )

            park.maxCapacity?.let { maxCapacity ->
                Text(
                    text = stringResource(R.string.max_capacity, maxCapacity),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            park.pricing?.let { pricingList ->
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))

                pricingList.forEach { pricing ->
                    val title = pricing.title[languageCode] ?: pricing.title["en"] ?: ""
                    val value = pricing.value[languageCode] ?: pricing.value["en"] ?: ""

                    Text(
                        text = "$title: $value",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

