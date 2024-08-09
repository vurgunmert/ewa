package com.vurgun.ewa.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlinx.coroutines.tasks.await
import android.util.Log

class RemoteConfigRepository {

    companion object {
        private const val GEMINI_APIKEY_REMOTE_KEY = "gemini_api_key"
        private const val TAG = "RemoteConfigRepository"
    }

    private val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

    fun fetchGeminiApiKey(): String {
        return try {
            remoteConfig.getString(GEMINI_APIKEY_REMOTE_KEY)
        } catch (e: Exception) {
            Log.e(TAG, "Error fetching API key: ${e.message}")
            throw e
        }
    }
}
