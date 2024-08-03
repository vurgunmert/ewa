package com.vurgun.ewa.presentation.games.matchgame.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repository.MatchGameRepository
import features.matchgame.viewmodel.MatchGameViewModel
import features.matchgame.viewmodel.SharedMatchGameViewModel
import kotlinx.coroutines.launch

class AndroidMatchGameViewModel(repository: MatchGameRepository) :
    ViewModel(), MatchGameViewModel by SharedMatchGameViewModel(repository) {

    init {
        viewModelScope.launch {
            loadTextElements()
        }
    }
}
