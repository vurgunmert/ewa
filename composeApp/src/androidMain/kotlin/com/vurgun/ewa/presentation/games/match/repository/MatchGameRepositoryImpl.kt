package com.vurgun.ewa.presentation.games.match.repository

import com.google.firebase.firestore.FirebaseFirestore
import data.repository.GameRepository
import data.repository.MatchGameRepository
import domain.model.EnglishLevel
import domain.model.Game
import domain.model.LevelConfiguration
import domain.model.TextElement
import domain.repository.GeminiRepository
import kotlinx.coroutines.tasks.await

class MatchGameRepositoryImpl(
    private val geminiRepository: GeminiRepository,
    private val gameRepository: GameRepository
) : MatchGameRepository {

    private val firestore = FirebaseFirestore.getInstance()
    private val currentGameState = mutableMapOf<String, Any>()

    override suspend fun getTextElements(
        game: Game,
        englishLevel: EnglishLevel,
        levelConfiguration: LevelConfiguration
    ): List<TextElement> = fetchTranslationWordsFromGemini(game, englishLevel, levelConfiguration)

    private suspend fun fetchWordsFromFirestore(levelId: String): List<TextElement> {
        return try {
            val snapshot = firestore.collection("academy")
                .document("resources")
                .collection("words")
                .whereEqualTo("level", levelId)  // Assuming you store the level info in Firestore
                .get()
                .await()

            snapshot.documents.mapNotNull { it.toObject(TextElement::class.java) }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    private suspend fun fetchTranslationWordsFromGemini(
        game: Game,
        englishLevel: EnglishLevel,
        levelConfig: LevelConfiguration,
    ): List<TextElement> {
        val prompt = generateGeminiPrompt(game, englishLevel, levelConfig)
        val response = geminiRepository.getWordTranslationList(prompt)

        return response.pairs.map { translation ->
            TextElement(
                id = translation.id,
                primaryText = translation.translations["en"] ?: "",
                secondaryText = translation.translations["tr"] ?: ""
            )
        }
    }

    private fun generateGeminiPrompt(
        game: Game,
        englishLevel: EnglishLevel,
        levelConfig: LevelConfiguration,
        topic: String? = null
    ): String {
        val promptTemplate = game.geminiPrompts.excludePrevious ?: game.geminiPrompts.general
        val interactedAnswers = getSeenAnswers().joinToString(", ")

        return promptTemplate
            .replace("{answerCount}", levelConfig.answerCount.toString())
            .replace("{englishLevel}", englishLevel.levelKey)
            .replace("{topic}", topic ?: "Representing english level topics")
            .replace("{interactedAnswers}", interactedAnswers)
    }

    //region Manage current game
    override fun addSeenAnswer(answer: String) {
        val seenAnswers = getSeenAnswers().toMutableList()
        seenAnswers.add(answer)
        currentGameState["seenAnswers"] = seenAnswers
    }

    override fun addSeenAnswers(answers: List<String>) {
        val seenAnswers = getSeenAnswers().toMutableList()
        seenAnswers.addAll(answers)
        currentGameState["seenAnswers"] = seenAnswers
    }

    override fun getSeenAnswers(): List<String> {
        return currentGameState["seenAnswers"] as? List<String> ?: emptyList()
    }

    override fun incrementCompletedFloor() {
        val currentFloor = getCompletedFloor()
        currentGameState["completedFloor"] = currentFloor + 1
    }

    override fun getCompletedFloor(): Int {
        return currentGameState["completedFloor"] as? Int ?: 0
    }

    override suspend fun endGame(gameId: String, levelId: String): Boolean {
        val game = gameRepository.getGame(gameId) ?: return false
        val levelConfig = game.levelConfiguration[levelId] ?: return false
        val totalFloors = levelConfig.floorCount

        if (getCompletedFloor() >= totalFloors) {
            saveGameState(gameId)
            resetGameState()
            return true
        } else {
            incrementCompletedFloor()
            saveGameState(gameId)
            return false
        }
    }

    override suspend fun saveGameState(gameId: String) {
        try {
            firestore.collection("academy")
                .document("game_states")
                .collection("games")
                .document(gameId)
                .set(currentGameState)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun loadGameState(gameId: String) {
        try {
            val snapshot = firestore.collection("academy")
                .document("game_states")
                .collection("games")
                .document(gameId)
                .get()
                .await()

            if (snapshot.exists()) {
                currentGameState.putAll(snapshot.data ?: emptyMap())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun resetGameState() {
        currentGameState.clear()
    }
}
