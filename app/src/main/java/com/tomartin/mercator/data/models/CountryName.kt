package com.tomartin.mercator.data.models

import kotlinx.serialization.Serializable

@Suppress("PLUGIN_IS_NOT_ENABLED")
@Serializable
data class CountryName(
    val common: String,
    val official: String
)
