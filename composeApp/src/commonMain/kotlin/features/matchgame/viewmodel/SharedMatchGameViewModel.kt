package features.matchgame.viewmodel

import data.repository.MatchGameRepository
import domain.model.EnglishLevel
import domain.model.Game
import domain.model.GameConfig
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

    private val _gameData = MutableStateFlow<GameConfig?>(null)
    override val gameData: StateFlow<GameConfig?> = _gameData

    private val _completed = MutableStateFlow(false)
    override val completed: StateFlow<Boolean> = _completed

    private lateinit var gameConfig: GameConfig

    override fun loadTextElements(game: GameConfig) {
        this.gameConfig = game
        launch {
            _gameData.value = game

            val elements = matchGameRepository.getTextElements(game)
            matchGameRepository.addSeenAnswers(elements.map { it.primaryText })
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

    override fun nextLevel() {
        launch {
            matchGameRepository.incrementCompletedFloor()

            val currentFloor = matchGameRepository.getCompletedFloor()
            val levelConfig = gameConfig.levelConfiguration

            if (currentFloor >= levelConfig.floorCount) {
                _completed.value = true
                saveGameState(gameConfig.gameId)
            } else {
                val newElements = matchGameRepository.getTextElements(gameConfig)
                matchGameRepository.addSeenAnswers(newElements.map { it.primaryText })
                _textElements.value = newElements
                saveGameState(gameConfig.gameId)
            }
        }
    }
}
