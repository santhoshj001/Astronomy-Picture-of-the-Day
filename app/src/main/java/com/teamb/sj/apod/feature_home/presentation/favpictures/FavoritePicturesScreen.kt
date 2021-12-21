package com.teamb.sj.apod.feature_home.presentation.favpictures

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.teamb.sj.apod.core.util.Screen
import com.teamb.sj.apod.feature_home.presentation.favpictures.components.FavoritePictureItem
import com.teamb.sj.apod.feature_home.presentation.picturedetail.components.PictureAppBar

@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun FavPictureScreen(
    navController: NavHostController,
    viewModel: FavoritesPictureViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Scaffold(
        topBar = {
            PictureAppBar(titleString = "Telescope")
        },
        content = {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(state.favoritePictures) { picture ->
                    FavoritePictureItem(
                        pictureDetail = picture,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                navController.navigate(
                                    Screen.PictureDetailScreen.createRouteWithDate(picture.date)
                                )
                            }
                    )
                }
            }
        }
    )
}
