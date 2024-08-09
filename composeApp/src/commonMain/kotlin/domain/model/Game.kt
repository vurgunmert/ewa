package domain.model

data class Game(
    val gameId: String = "",
    val gameType: GameType = GameType.MATCH,
    val names: Map<String, String> = emptyMap(),
    val descriptions: Map<String, String> = emptyMap(),
    val geminiPrompts: Prompts = Prompts(),
    val levelConfiguration: Map<String, LevelConfiguration> = emptyMap() // Private field for direct mapping
) {
    fun toConfig(levelId: String): GameConfig {
        val englishLevel = EnglishLevel.fromKey(levelId)
        val levelConfig = levelConfiguration[englishLevel.levelKey] ?: LevelConfiguration(6, 4)
        return GameConfig(
            gameId = gameId,
            gameType = gameType,
            names = names,
            descriptions = descriptions,
            geminiPrompts = geminiPrompts,
            englishLevel = englishLevel,
            levelConfiguration = levelConfig
        )
    }
}