package data.repository

import domain.model.Game

interface GameRepository {
    suspend fun getGame(gameId: String): Game?
}