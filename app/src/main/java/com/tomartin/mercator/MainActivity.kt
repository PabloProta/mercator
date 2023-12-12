package com.tomartin.mercator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tomartin.mercator.ui.screens.DetailedCountryScreen
import com.tomartin.mercator.ui.screens.GlanceCountryScreen
import com.tomartin.mercator.ui.theme.MercatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MercatorTheme {
                NavHost(navController, "glanceScreen") {
                    composable("glanceScreen") { GlanceCountryScreen(navController) }
                    composable("detailedScreen") { DetailedCountryScreen(navController) }
                }
            }
        }
    }
}