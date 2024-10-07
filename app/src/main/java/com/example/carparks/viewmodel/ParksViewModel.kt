package com.example.carparks.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carparks.model.CarPark
import com.example.carparks.model.ParksApi
import com.example.carparks.model.GraphQLRequest
import kotlinx.coroutines.launch
import android.util.Log

class ParksViewModel : ViewModel() {
    var parks = mutableStateOf<List<CarPark>>(emptyList())
        private set
    var loading = mutableStateOf(false)
        private set
    var error = mutableStateOf<String?>(null)
        private set

    init {
        fetchParks()
    }

    private fun fetchParks() {
        val query = """
            {
                carParks {
                    carParkId
                    name
                    lat
                    lon
                    maxCapacity
                    spacesAvailable
                    pricing {
                        title { fi }
                        value { fi }
                    }
                }
            }
        """.trimIndent()

        loading.value = true

        viewModelScope.launch {
            try {
                val parksApi = ParksApi.getInstance()
                val response = parksApi.getParks(GraphQLRequest(query))

//                Log.d("ParksViewModel", "API response: $response")
//
//                Log.d("ParksViewModel", "Car parks data: ${response.data.carParks}")

                val distinctParks = response.data.carParks.distinctBy { it.name }

                val sortedParks = distinctParks.sortedWith(
                    compareByDescending<CarPark?> { it?.spacesAvailable != null }
                        .thenByDescending { it?.pricing != null }
                )

                parks.value = sortedParks

            } catch (e: Exception) {
                error.value = e.message
                Log.e("ParksViewModel", "Error fetching data: ${e.message}")
            } finally {
                loading.value = false
            }
        }
    }
}