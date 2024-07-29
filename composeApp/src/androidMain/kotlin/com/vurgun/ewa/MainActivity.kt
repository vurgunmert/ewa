package com.vurgun.ewa

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import features.userpreferences.UserPreferencesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UserPreferencesScreen{handleGoogleSign()}
        }
    }

    private fun handleGoogleSign() {
        //TODO: Integrate Firebase google sign in
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}