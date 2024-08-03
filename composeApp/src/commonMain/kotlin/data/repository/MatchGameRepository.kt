package data.repository

import domain.model.TextElement

interface MatchGameRepository {
    suspend fun getTextElements(): List<TextElement>
}