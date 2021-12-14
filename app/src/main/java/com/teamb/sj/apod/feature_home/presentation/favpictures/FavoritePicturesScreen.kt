package com.teamb.sj.apod.feature_home.presentation.favpictures

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.teamb.sj.apod.core.util.Screen
import com.teamb.sj.apod.feature_home.presentation.favpictures.components.FavoritePictureItem

@Composable
fun FavPictureScreen(
    navController: NavHostController,
    viewModel: FavoritesPictureViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scaffoldState = rememberScaffoldState()

    viewModel.getFavoritePictures()

    Scaffold(scaffoldState = scaffoldState) {
        Column {
            TopAppBar(
                elevation = 8.dp,
                title = {
                    Text("Favorites")
                },
            )
            LazyColumn(modifier = Modifier.fillMaxSize().padding(4.dp)) {
                items(state.favoritePictures) { picture ->
                    FavoritePictureItem(
                        pictureDetail = picture,
                        cornerRadius = 4.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.PictureDetailScreen.createRouteWithDate(picture.date)
                                )
                            }
                    )
                }
            }
        }
    }
}