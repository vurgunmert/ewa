package features.matchgame.viewmodel

import data.repository.MatchGameRepository
import domain.model.TextElement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedMatchGameViewModel(private val repository: MatchGameRepository) : MatchGameViewModel, CoroutineScope by MainScope() {

    private val _textElements = MutableStateFlow<List<TextElement>>(emptyList())
    override val textElements: StateFlow<List<TextElement>> get() = _textElements

    override fun loadTextElements() {
        launch {
            _textElements.value = repository.getTextElements()
        }
    }
}
