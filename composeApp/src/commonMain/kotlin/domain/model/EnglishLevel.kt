package domain.model;

enum class EnglishLevel(val levelKey: String) {
    ENGLISH_LEVEL_A1("english_level_a1"),
    ENGLISH_LEVEL_A2("english_level_a2"),
    ENGLISH_LEVEL_B1("english_level_b1"),
    ENGLISH_LEVEL_B1_MINUS("english_level_b1_minus"),
    ENGLISH_LEVEL_B1_PLUS("english_level_b1_plus"),
    ENGLISH_LEVEL_B2("english_level_b2"),
    ENGLISH_LEVEL_C1("english_level_c1"),
    ENGLISH_LEVEL_C2("english_level_c2");

    companion object {
        fun fromKey(key: String): EnglishLevel {
            return values().find { it.levelKey == key } ?: EnglishLevel.ENGLISH_LEVEL_B2
        }
    }
}