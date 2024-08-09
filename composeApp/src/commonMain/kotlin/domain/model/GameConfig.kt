package domain.model

data class GameConfig(
    val gameId: String,
    val gameType: GameType,
    val names: Map<String, String>,
    val descriptions: Map<String, String>,
    val geminiPrompts: Prompts,
    val englishLevel: EnglishLevel,
    val levelConfiguration: LevelConfiguration
)