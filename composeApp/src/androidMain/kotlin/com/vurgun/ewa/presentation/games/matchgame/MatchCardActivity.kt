package com.vurgun.ewa.presentation.games.matchgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.vurgun.ewa.presentation.games.matchgame.ui.AndroidMatchGameViewModel
import com.vurgun.ewa.presentation.games.matchgame.ui.MatchGameScreen
import org.koin.androidx.viewmodel.ext.android.viewModel

class MatchCardActivity : ComponentActivity() {

    private val matchGameViewModel: AndroidMatchGameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatchGameScreen(matchGameViewModel)
        }
    }
}
