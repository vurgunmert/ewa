package com.vurgun.ewa.presentation.gamehost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repository.GameRepository
import domain.model.GameConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class GameHostViewState {
    data object Start : GameHostViewState()
    data object Loading : GameHostViewState()
    data class Ready(val game: GameConfig) : GameHostViewState()
    data object GameNotFound : GameHostViewState()
}

class GameHostViewModel(private val gameRepository: GameRepository) : ViewModel() {

    private val _viewState = MutableStateFlow<GameHostViewState>(GameHostViewState.Start)
    val viewState: StateFlow<GameHostViewState> = _viewState

    fun loadGame(id: String, levelId: String) {
        _viewState.value = GameHostViewState.Loading

        viewModelScope.launch {
            val game = gameRepository.getGame(id)
            if (game != null) {
                _viewState.value = GameHostViewState.Ready(game.toConfig(levelId))
            } else {
                _viewState.value = GameHostViewState.GameNotFound
            }
        }
    }
}