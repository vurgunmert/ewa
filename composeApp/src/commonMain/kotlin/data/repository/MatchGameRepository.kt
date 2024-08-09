package data.repository

import domain.model.GameConfig
import domain.model.TextElement

interface MatchGameRepository {
    suspend fun getTextElements(game: GameConfig): List<TextElement>
    suspend fun endGame(gameId: String, levelId: String): Boolean
    fun addSeenAnswer(answer: String)
    fun addSeenAnswers(answers: List<String>)
    fun getSeenAnswers(): List<String>
    fun incrementCompletedFloor()
    fun getCompletedFloor(): Int
    suspend fun saveGameState(gameId: String)
    suspend fun loadGameState(gameId: String)
}
