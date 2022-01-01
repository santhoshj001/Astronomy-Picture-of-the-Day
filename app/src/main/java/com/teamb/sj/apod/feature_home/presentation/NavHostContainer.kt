package com.teamb.sj.apod.feature_home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teamb.sj.apod.core.util.Constants
import com.teamb.sj.apod.core.util.Screen
import com.teamb.sj.apod.feature_home.presentation.favpictures.FavPictureScreen
import com.teamb.sj.apod.feature_home.presentation.picturedetail.PictureDetailScreen

@ExperimentalMaterialApi
@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
) {

    Box {
        NavHost(
            navController = navController,

            startDestination = Screen.PictureDetailScreen.route +
                "?picDate={${Constants.PIC_DATE}}",
            // Set the padding provided by scaffold
            modifier = Modifier.padding(paddingValues = padding),
            builder = {
                // route : Home
                composable(
                    Screen.PictureDetailScreen.route + "?picDate={${Constants.PIC_DATE}}",
                    arguments = listOf(
                        navArgument(
                            name = Constants.PIC_DATE
                        ) {
                            type = NavType.StringType
                            nullable = true
                        }
                    )
                ) {
                    val date = it.arguments?.getLong(Constants.PIC_DATE)
                    if (date != null) {
                        PictureDetailScreen(navController, date)
                    }
                }

                // route : favorites
                composable(Screen.FavPictureScreen.route) {
                    FavPictureScreen(navController)
                }
            }
        )
    }
}
