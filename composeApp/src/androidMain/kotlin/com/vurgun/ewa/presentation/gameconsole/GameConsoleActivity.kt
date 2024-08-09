package com.vurgun.ewa.presentation.gameconsole

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

class GameConsoleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GameConsoleScreen(modifier = Modifier.fillMaxSize())
        }
    }

    private fun handleGoogleSign() {
        //TODO: Integrate Firebase google sign in
    }

    private fun navigateToGame(level: Int, gameId: Int) {

    }
}