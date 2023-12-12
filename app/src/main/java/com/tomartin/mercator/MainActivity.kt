package com.tomartin.mercator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.tomartin.mercator.ui.screens.GlanceCountryScreen
import com.tomartin.mercator.ui.theme.MercatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MercatorTheme {
                GlanceCountryScreen()
            }
        }
    }
}