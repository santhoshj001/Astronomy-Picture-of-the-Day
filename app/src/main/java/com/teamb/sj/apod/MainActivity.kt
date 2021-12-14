package com.teamb.sj.apod

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.teamb.sj.apod.feature_home.presentation.BottomNavigationBar
import com.teamb.sj.apod.feature_home.presentation.NavHostContainer
import com.teamb.sj.apod.ui.theme.APODTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            APODTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = { BottomNavigationBar(navController) },
                        content = { paddingValues ->
                            NavHostContainer(navController = navController, padding = paddingValues)
                        }
                    )
                }
            }
        }
    }
}

