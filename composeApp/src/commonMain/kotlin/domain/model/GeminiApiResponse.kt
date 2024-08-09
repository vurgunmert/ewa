package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiTranslationPairsResponse(
    val pairs: List<TranslationPair>
)