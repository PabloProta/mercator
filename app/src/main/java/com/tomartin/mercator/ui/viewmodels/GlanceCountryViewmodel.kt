package com.tomartin.mercator.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tomartin.mercator.data.models.GlanceCountryModel
import com.tomartin.mercator.data.network.AtlasRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class GlanceScreenUiState(
    val atlasData: List<GlanceCountryModel>? = null
)

class GlanceCountryViewmodel(): ViewModel() {

    private val _uiState = MutableStateFlow(GlanceScreenUiState())
    val uiState: StateFlow<GlanceScreenUiState> = _uiState.asStateFlow()

    suspend fun fetchAtlasData() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(atlasData = AtlasRepository().fetchAtGlanceCountryData())
            }
        }
    }
}