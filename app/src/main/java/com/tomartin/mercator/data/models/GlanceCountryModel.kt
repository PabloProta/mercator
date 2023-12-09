package com.tomartin.mercator.data.models

import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class GlanceCountryModel(
    val name: CountryName,
    val population: Int,
    val flags: CountryFlag
)
