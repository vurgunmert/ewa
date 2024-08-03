package features.matchgame.viewmodel

import domain.model.TextElement
import kotlinx.coroutines.flow.StateFlow

interface MatchGameViewModel {
    val textElements: StateFlow<List<TextElement>>
    fun loadTextElements()
}
