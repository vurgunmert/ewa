package domain.model

data class Prompts(
    val general: String = "",
    val topicRelated: String? = null,
    val excludePrevious: String? = null
)