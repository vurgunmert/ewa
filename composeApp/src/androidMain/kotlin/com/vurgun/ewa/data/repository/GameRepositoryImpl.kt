package com.vurgun.ewa.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import data.repository.GameRepository
import domain.model.Game
import kotlinx.coroutines.tasks.await

class GameRepositoryImpl : GameRepository {
    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun getGame(gameId: String): Game? {
        return try {
            val gameDocument = firestore.collection("academy")
                .document("academy_001")
                .collection("games")
                .document(gameId)
                .get()
                .await()
            gameDocument.toObject(Game::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}