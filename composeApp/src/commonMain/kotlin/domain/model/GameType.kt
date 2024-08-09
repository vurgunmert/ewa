package domain.model

enum class GameType(val typeKey: String) {
    MATCH("MATCH"),
    ORDER("ORDER"),
    FILL("FILL"),
    SURROGATE("SURROGATE"),
    EXAM_PRACTICE("EXAM_PRACTICE"),
    JOB_SIMULATION("JOB_SIMULATION");

    companion object {
        fun fromKey(key: String): GameType? {
            return values().find { it.typeKey == key }
        }
    }
}