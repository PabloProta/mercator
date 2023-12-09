package com.tomartin.mercator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tomartin.mercator.ui.theme.MercatorTheme
import com.tomartin.mercator.ui.viewmodels.GlanceCountryViewmodel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MercatorTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(glanceCountryViewmodel: GlanceCountryViewmodel = viewModel()) {
    val uiState by glanceCountryViewmodel.uiState.collectAsState()
    LaunchedEffect(key1 = uiState, block = { glanceCountryViewmodel.fetchAtlasData() })
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            uiState.atlasData?.let { glanceCountryModels ->
                items(glanceCountryModels.size) {
                    uiState.atlasData?.get(it)?.name?.let { it1 -> Text(text = it1.common) }
                }
            }
        }
    }
}