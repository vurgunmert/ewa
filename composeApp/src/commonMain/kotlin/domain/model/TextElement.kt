package domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TextElement(
    val id: String,
    val primaryText: String,
    val secondaryText: String,
)