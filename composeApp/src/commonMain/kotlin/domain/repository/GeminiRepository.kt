package domain.repository

import domain.model.GeminiTranslationPairsResponse

interface GeminiRepository {
    suspend fun getWordTranslationList(prompt: String): GeminiTranslationPairsResponse
}
