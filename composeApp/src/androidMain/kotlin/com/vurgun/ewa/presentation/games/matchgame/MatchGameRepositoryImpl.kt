package com.vurgun.ewa.presentation.games.matchgame

import com.google.firebase.firestore.firestore
import com.google.firebase.Firebase
import data.repository.MatchGameRepository
import domain.model.TextElement
import kotlinx.coroutines.tasks.await

class MatchGameRepositoryImpl : MatchGameRepository {
    override suspend fun getTextElements(): List<TextElement> {
        val db = Firebase.firestore
        return try {
            val snapshot = db.collection("academy")
                .document("resources")
                .collection("words")
                .get()
                .await()
            snapshot.documents.mapNotNull { it.toObject(TextElement::class.java) }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}