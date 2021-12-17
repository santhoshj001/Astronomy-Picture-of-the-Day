package com.teamb.sj.apod.feature_home.presentation.picturedetail

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.teamb.sj.apod.core.util.Screen
import com.teamb.sj.apod.core.util.Utils
import com.teamb.sj.apod.feature_home.presentation.picturedetail.components.PictureAppBar
import com.teamb.sj.apod.feature_home.presentation.picturedetail.components.PictureDescription
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExperimentalMaterial3Api
@Composable
fun PictureDetailScreen(
    navController: NavController,
    date: String?,
    viewModel: PictureDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val searchDateState = viewModel.searchDate.value
    val favoriteState = viewModel.favState.value
    //val scaffoldState = rememberScaffoldState()

    Log.i("tracking", "PictureDetailScreen: $date")

    date?.let { dateString ->
        if (!searchDateState.contentEquals(dateString)) {
            viewModel.updateDate(Utils.getLocalDateFromString(dateString))
        }
    }
    val selectedDate = Utils.getLocalDateFromString(searchDateState)
    val datePickerDialog = DatePickerDialog(
        LocalContext.current, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val localDate = LocalDate.of(year, month + 1, dayOfMonth)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            navController.navigate(
                Screen.PictureDetailScreen.createRouteWithDate(
                    localDate.format(
                        formatter
                    )
                )
            ) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = false
                }
                launchSingleTop = true
            }
        }, selectedDate.year, selectedDate.monthValue - 1, selectedDate.dayOfMonth
    )


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PictureDetailViewModel.UIEvent.ShowSnackBar -> {
                    /*  scaffoldState.snackbarHostState.showSnackbar(
                          message = event.message
                      )*/
                }
            }
        }
    }

    val scrollState = rememberScrollState()
    val scrollBehavior = remember { TopAppBarDefaults.pinnedScrollBehavior() }

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { datePickerDialog.show() },
                icon = { Icon(Icons.Filled.DateRange, "Localized description") },
                text = { Text(text = Utils.getHumanReadableDate(searchDateState)) },
            )
        },
        topBar = {
            PictureAppBar(
                scrollBehavior = scrollBehavior,
                titleString = "Telescope"
            )
        }
    ) {
        Column() {
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Loading..")

                }
            } else {
                if (state.pictureDetail.date.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable { }
                    ) {
                        Column {
                            PictureDescription(
                                picture = state.pictureDetail,
                                favoriteState,
                                viewModel::toggleFavorite
                            )
                        }
                    }
                }
            }
        }
    }
}