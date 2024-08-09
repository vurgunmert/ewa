package com.vurgun.ewa.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.FunctionType
import com.google.ai.client.generativeai.type.Schema
import com.google.ai.client.generativeai.type.generationConfig
import domain.model.GeminiTranslationPairsResponse
import domain.repository.GeminiRepository
import kotlinx.serialization.json.Json

class GeminiApiRepositoryImpl(
    remoteConfigRepository: RemoteConfigRepository
) : GeminiRepository {

    private val model = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = remoteConfigRepository.fetchGeminiApiKey(),
        generationConfig = generationConfig {
            temperature = 0f
            topP = 0.95f
            topK = 64
            maxOutputTokens = 8192
            responseMimeType = "application/json"
            responseSchema = Schema(
                name = "response",
                description = "Response object containing pairs",
                type = FunctionType.OBJECT,
                properties = mapOf(
                    "pairs" to Schema(
                        name = "pairs",
                        description = "List of translation pairs",
                        type = FunctionType.ARRAY,
                        items = Schema(
                            name = "translationPair",
                            description = "A translation pair",
                            type = FunctionType.OBJECT,
                            required = listOf("id", "translations"),
                            properties = mapOf(
                                "id" to Schema(
                                    name = "id",
                                    description = "Unique identifier",
                                    type = FunctionType.STRING
                                ),
                                "translations" to Schema(
                                    name = "translations",
                                    description = "Translations in English and Turkish",
                                    type = FunctionType.OBJECT,
                                    required = listOf("en", "tr"),
                                    properties = mapOf(
                                        "en" to Schema(
                                            name = "en",
                                            description = "English translation",
                                            type = FunctionType.STRING
                                        ),
                                        "tr" to Schema(
                                            name = "tr",
                                            description = "Turkish translation",
                                            type = FunctionType.STRING
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
        }
    )

    override suspend fun getWordTranslationList(prompt: String): GeminiTranslationPairsResponse {
        val response = model.generateContent(prompt)
        return parseResponse(response.text!!) //TODO: handle optional
    }

    private fun parseResponse(response: String): GeminiTranslationPairsResponse {
        return Json.decodeFromString(GeminiTranslationPairsResponse.serializer(), response)
    }
}
