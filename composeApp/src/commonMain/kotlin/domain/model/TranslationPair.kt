package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TranslationPair(
    val id: String,
    val translations: Map<String, String>
)