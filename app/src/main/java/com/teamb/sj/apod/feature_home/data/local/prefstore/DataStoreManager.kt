package com.teamb.sj.apod.feature_home.data.local.prefstore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.teamb.sj.apod.core.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("settings")

class DataStoreManager @Inject constructor(@ApplicationContext appContext: Context) {

    private val settingsDataStore = appContext.dataStore
    private val dateKey = longPreferencesKey("date")

    suspend fun setRecentlyUsedDate(date: Long) {
        settingsDataStore.edit { settings ->
            settings[dateKey] = date
        }
    }

    val getRecentlyUsedDate: Flow<Long> = settingsDataStore.data.map { preferences ->
        preferences[dateKey] ?: Constants.DEFAULT_DATE
    }
}
