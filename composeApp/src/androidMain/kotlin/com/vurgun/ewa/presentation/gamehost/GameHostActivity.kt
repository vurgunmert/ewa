package com.vurgun.ewa.presentation.gamehost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class GameHostActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gameId = intent.getStringExtra("GAME_ID") ?: "game_match_en_tr_words"
        val levelId = intent.getStringExtra("LEVEL_ID") ?: "english_level_b2"

        setContent {
            GameHostScreen(gameId, levelId)
        }
    }
}

