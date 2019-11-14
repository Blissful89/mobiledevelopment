package com.example.gamebacklog.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.gamebacklog.dao.GameDao
import com.example.gamebacklog.database.GameRoomDatabase
import com.example.gamebacklog.model.Game

class GameRepository(context: Context) {

    private val gameDao: GameDao

    init {
        val database = GameRoomDatabase.getDatabase(context)
        gameDao = database!!.gameDao()
    }

    fun getAllGames(): LiveData<List<Game>> = gameDao.getAllGames()

    suspend fun insertGame(game: Game) = gameDao.insertGame(game)

    suspend fun deleteGame(game: Game) = gameDao.deleteGame(game)

    suspend fun deleteAllGames() = gameDao.deleteAllPGames()
}