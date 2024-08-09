package features.matchgame.viewmodel

import domain.model.EnglishLevel
import domain.model.Game
import domain.model.LevelConfiguration
import domain.model.TextElement
import kotlinx.coroutines.flow.StateFlow

interface MatchGameViewModel {
    val textElements: StateFlow<List<TextElement>>
    val gameData: StateFlow<Game?>
    val completed: StateFlow<Boolean>

    fun loadTextElements(game: Game, englishLevel: EnglishLevel, levelConfiguration: LevelConfiguration)
    fun incrementFloorAndCheckCompletion(gameId: String, levelId: String)
    fun addSeenAnswer(answer: String)
    fun getSeenAnswers(): List<String>
    fun getCompletedFloor(): Int
    fun saveGameState(gameId: String)
    fun loadGameState(gameId: String)
}