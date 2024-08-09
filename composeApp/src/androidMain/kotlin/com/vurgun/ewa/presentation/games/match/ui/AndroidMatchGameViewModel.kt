package com.vurgun.ewa.presentation.games.match.ui

import androidx.lifecycle.ViewModel
import data.repository.MatchGameRepository
import features.matchgame.viewmodel.MatchGameViewModel
import features.matchgame.viewmodel.SharedMatchGameViewModel

class AndroidMatchGameViewModel(
    repository: MatchGameRepository
) : ViewModel(),
    MatchGameViewModel by SharedMatchGameViewModel(repository)