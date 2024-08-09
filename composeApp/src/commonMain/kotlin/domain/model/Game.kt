package domain.model

data class Game(
    val gameId: String = "",
    val gameType: GameType = GameType.MATCH,
    val names: Map<String, String> = emptyMap(),
    val descriptions: Map<String, String> = emptyMap(),
    val geminiPrompts: Prompts = Prompts(),
    val levelConfiguration: Map<String, LevelConfiguration> = emptyMap() // Private field for direct mapping
) {
    val levelConfigurations: Map<EnglishLevel, LevelConfiguration>
        get() = levelConfiguration.mapKeys { (key, _) ->
            EnglishLevel.fromKey(key) ?: throw IllegalArgumentException("Unknown level key: $key")
        }
}