package com.vurgun.ewa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import features.elemental.ElementalOverviewScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ElementalOverviewScreen()
//            UserPreferencesScreen{handleGoogleSign()}
        }
    }

    private fun handleGoogleSign() {
        //TODO: Integrate Firebase google sign in
    }
}