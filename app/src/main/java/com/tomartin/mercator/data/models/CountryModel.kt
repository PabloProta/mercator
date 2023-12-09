package com.tomartin.mercator.data.models

import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class CountryModel(
    val name: CountryName,
    val independent: Boolean,
    val capital: List<String>,
    val region: String,
    val population: Int,
    val continents: List<String>,
    val flags: CountryFlag,
)
