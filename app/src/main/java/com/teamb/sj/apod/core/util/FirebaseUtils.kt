package com.teamb.sj.apod.core.util

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object FirebaseUtils {
    private val TAG: String = FirebaseUtils.javaClass.simpleName
    fun initFirebase() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                Log.d(TAG, "Config params updated: $updated")
            } else {
                Log.d(TAG, "Config params update failed")
            }
        }
    }

    fun getApiKey(): String {
        return Firebase.remoteConfig.getString(Constants.API_KEY)
    }

    fun isSetWallpaperEnabled(): Boolean {
        return Firebase.remoteConfig.getBoolean(Constants.WALLPAPER)
    }
}
