package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TextElement(
    val id: Int = -1,
    val name: String = "unknown",
    val level: String = "A1",
    val category: String = "unknown",
    val type: String = "unknown",
    val translations: Map<String, String> = emptyMap()
)
