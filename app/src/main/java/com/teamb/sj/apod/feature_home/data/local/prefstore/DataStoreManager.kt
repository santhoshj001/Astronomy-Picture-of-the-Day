package com.teamb.sj.apod.feature_home.data.local.prefstore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.teamb.sj.apod.core.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val settingsDataStore = appContext.dataStore
    private val dateKey = stringPreferencesKey("date")

    suspend fun setRecentlyUsedDate(date: String) {
        settingsDataStore.edit { settings ->
            settings[dateKey] = date
        }
    }

    val getRecentlyUsedDate: Flow<String> = settingsDataStore.data.map { preferences ->
        preferences[dateKey] ?: Constants.DEFAULT
    }

}