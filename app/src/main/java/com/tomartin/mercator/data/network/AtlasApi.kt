package com.tomartin.mercator.data.network

import com.tomartin.mercator.data.models.CountryModel
import com.tomartin.mercator.data.models.GlanceCountryModel

interface AtlasApi {
    suspend fun fetchAtGlanceCountryData(): List<GlanceCountryModel>

    suspend fun fetchWholeCountryData(): CountryModel
}