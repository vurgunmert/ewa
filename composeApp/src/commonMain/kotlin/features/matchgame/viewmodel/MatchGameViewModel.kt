package features.matchgame.viewmodel

import domain.model.GameConfig
import domain.model.TextElement
import kotlinx.coroutines.flow.StateFlow

interface MatchGameViewModel {
    val textElements: StateFlow<List<TextElement>>
    val gameData: StateFlow<GameConfig?>
    val completed: StateFlow<Boolean>

    fun loadTextElements(game: GameConfig)
    fun incrementFloorAndCheckCompletion(gameId: String, levelId: String)
    fun addSeenAnswer(answer: String)
    fun getSeenAnswers(): List<String>
    fun getCompletedFloor(): Int
    fun saveGameState(gameId: String)
    fun loadGameState(gameId: String)
    fun nextLevel()
}