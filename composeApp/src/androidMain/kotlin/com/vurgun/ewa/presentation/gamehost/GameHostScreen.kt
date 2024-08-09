package com.vurgun.ewa.presentation.gamehost

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vurgun.ewa.presentation.games.match.ui.MatchGameScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun GameHostScreen(
    gameId: String,
    levelId: String,
    viewModel: GameHostViewModel = koinViewModel()) {
    val viewState by viewModel.viewState.collectAsState()

    when (viewState) {
        is GameHostViewState.Start -> {
            viewModel.loadGame(gameId, levelId)
        }
        is GameHostViewState.Loading -> {
            CircularProgressIndicator()
        }

        is GameHostViewState.Ready -> {
            val state = (viewState as GameHostViewState.Ready)
            MatchGameScreen(state.game)
        }

        is GameHostViewState.GameNotFound -> {
            Text(text = "GAME NOT FOUND!")
        }
    }
}
