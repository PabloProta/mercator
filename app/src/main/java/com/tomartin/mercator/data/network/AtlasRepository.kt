package com.tomartin.mercator.data.network

import com.tomartin.mercator.data.models.CountryModel
import com.tomartin.mercator.data.models.GlanceCountryModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class AtlasRepository : AtlasApi {

    private fun httpClient(): HttpClient {
        val client = HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
        return client
    }

    override suspend fun fetchAtGlanceCountryData(): List<GlanceCountryModel> =
        withContext(Dispatchers.IO) {
            httpClient().get("https://restcountries.com/v3.1/all")
                .body<List<GlanceCountryModel>>()
        }

    override suspend fun fetchWholeCountryData(): CountryModel {
        TODO("Not yet implemented")
    }
}