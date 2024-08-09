package features.matchgame.viewmodel

import data.repository.MatchGameRepository
import domain.model.EnglishLevel
import domain.model.Game
import domain.model.LevelConfiguration
import domain.model.TextElement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedMatchGameViewModel(
    private val matchGameRepository: MatchGameRepository
) : MatchGameViewModel, CoroutineScope by MainScope() {

    private val _textElements = MutableStateFlow<List<TextElement>>(emptyList())
    override val textElements: StateFlow<List<TextElement>> = _textElements

    private val _gameData = MutableStateFlow<Game?>(null)
    override val gameData: StateFlow<Game?> = _gameData

    private val _completed = MutableStateFlow(false)
    override val completed: StateFlow<Boolean> = _completed

    override fun loadTextElements(
        game: Game,
        englishLevel: EnglishLevel,
        levelConfiguration: LevelConfiguration
    ) {
        launch {
            _gameData.value = game

            val elements = matchGameRepository.getTextElements(game, englishLevel, levelConfiguration)
            _textElements.value = elements
        }
    }

    override fun incrementFloorAndCheckCompletion(gameId: String, levelId: String) {
        launch {
            val isGameEnded = matchGameRepository.endGame(gameId, levelId)
            _completed.value = isGameEnded
        }
    }

    override fun addSeenAnswer(answer: String) {
        matchGameRepository.addSeenAnswer(answer)
    }

    override fun getSeenAnswers(): List<String> {
        return matchGameRepository.getSeenAnswers()
    }

    override fun getCompletedFloor(): Int {
        return matchGameRepository.getCompletedFloor()
    }

    override fun saveGameState(gameId: String) {
        launch {
            matchGameRepository.saveGameState(gameId)
        }
    }

    override fun loadGameState(gameId: String) {
        launch {
            matchGameRepository.loadGameState(gameId)
        }
    }
}
