package com.teamb.sj.apod.feature_home.presentation.picturedetail

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.teamb.sj.apod.feature_home.presentation.picturedetail.components.PictureAppBar
import com.teamb.sj.apod.feature_home.presentation.picturedetail.components.PictureDetailView
import java.time.LocalDate
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun PictureDetailScreen(
    navController: NavController,
    date: Long,
    viewModel: PictureDetailViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val searchDateState = viewModel.searchDate.value
    val favoriteState = viewModel.favState.value
    val scaffoldState = rememberScaffoldState()

    /*date?.let { dateString ->
        if (!searchDateState.contentEquals(dateString)) {
            viewModel.updateDate(Utils.getLocalDateFromString(dateString))
        }
    }*/
    val datePickerDialog = DatePickerDialog(
        LocalContext.current, { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedDate = LocalDate.of(year, month, dayOfMonth)
            viewModel.updateDate(selectedDate)
        }, searchDateState.year, searchDateState.monthValue, searchDateState.dayOfMonth
    )
    datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

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
        scaffoldState = scaffoldState,
        topBar = {
            PictureAppBar(
                titleString = "Telescope",
                actions = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                    }
                }
            )
        },
        backgroundColor = MaterialTheme.colorScheme.background
    ) {
        Column {
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Loading..")
                }
            } else {
                if (state.pictureDetail.date > 0) {
                    LazyColumn {
                        item {
                            PictureDetailView(
                                picture = state.pictureDetail,
                                favoriteState = favoriteState,
                                toggleFav = viewModel::toggleFavorite,
                                viewModel::sendSnackMessage
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(150.dp))
                        }
                    }
                }
            }
        }
    }
}
