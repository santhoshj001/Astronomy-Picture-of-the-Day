package com.teamb.sj.apod.feature_home.presentation.PictureDetail

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import android.widget.ProgressBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.teamb.sj.apod.core.util.Screen
import com.teamb.sj.apod.core.util.Utils
import com.teamb.sj.apod.feature_home.presentation.PictureDetail.components.PictureDescription
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun PictureDetailScreen(
    navController: NavController,
    date: String?,
    viewModel: PictureDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val searchDateState = viewModel.searchDate.value
    val favoriteState = viewModel.favState.value
    val scaffoldState = rememberScaffoldState()


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
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Filled.DateRange, "") },
                text = { Text(text = searchDateState) },
                onClick = { datePickerDialog.show() },
                elevation = FloatingActionButtonDefaults.elevation(16.dp)
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column() {
            TopAppBar(
                elevation = 8.dp,
                title = {
                    Text("Astronomy Pic of the Day.")
                },
            )
            if (state.isLoading) {
                Box(
                    modifier = Modifier.padding(10.dp).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Loading..")

                }
            } else {
                if (state.pictureDetail.date.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable { },
                        elevation = 10.dp,
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