package com.teamb.sj.apod.feature_home.presentation.favpictures

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail
import com.teamb.sj.apod.feature_home.presentation.favpictures.components.FavoritePictureItem
import com.teamb.sj.apod.feature_home.presentation.picturedetail.components.PictureAppBar
import com.teamb.sj.apod.feature_home.presentation.picturedetail.components.PictureDetailView
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun FavPictureScreen(
    navController: NavHostController,
    viewModel: FavoritesPictureViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    viewModel.getFavoritePictures()

    var pic = remember {
        mutableStateOf(PictureDetail())
    }
    val coroutineScope = rememberCoroutineScope()
    val bottomState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(
        sheetState = bottomState,
        sheetContent = {
            PictureDetailView(
                picture = pic.value,
                favoriteState = true,
                toggleFav = {},
                sendMessage = {}
            )
        }
    ) {
        Scaffold(
            backgroundColor = MaterialTheme.colorScheme.background,
            topBar = {
                PictureAppBar(titleString = "TeleScope")
            },
            content = {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                ) {
                    items(state.favoritePictures) { picture ->
                        FavoritePictureItem(
                            pictureDetail = picture,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clickable { /*
                                    navController.navigate(
                                        Screen.PictureDetailScreen.createRouteWithDate(picture.date)
                                    )*/
                                    pic.value = picture
                                    coroutineScope.launch { bottomState.show() }
                                }
                        )
                    }
                }
            }
        )
    }
}
