package domain.model

data class Prompts(
    val general: String = "",
    val topicRelated: String? = null,
    val excludeSeen: String? = null
)