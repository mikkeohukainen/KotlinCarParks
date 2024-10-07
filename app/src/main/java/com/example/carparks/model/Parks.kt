package com.example.carparks.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

data class Pricing(
    val title: Map<String, String>,
    val value: Map<String, String>
)

data class CarPark(
    val carParkId: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val maxCapacity: Int?,
    val spacesAvailable: Int?,
    val pricing: List<Pricing>?
)

data class CarParksResponse(
    val data: CarParksData
)

data class CarParksData(
    val carParks: List<CarPark>
)

data class GraphQLRequest(
    val query: String
)

const val BASE_URL = "https://api.oulunliikenne.fi/proxy/graphql/"

interface ParksApi {
    @POST(".")
    suspend fun getParks(@Body request: GraphQLRequest): CarParksResponse

    companion object {
        var parksService: ParksApi? = null

        fun getInstance(): ParksApi {
            if (parksService === null) {
                parksService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ParksApi::class.java)
            }
            return parksService!!
        }
    }
}
