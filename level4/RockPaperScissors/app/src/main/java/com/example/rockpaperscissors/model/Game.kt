package com.example.rockpaperscissors.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "games")
data class Game(

    @ColumnInfo(name = "player")
    @DrawableRes val playerChoice: Int,

    @ColumnInfo(name = "computer")
    @DrawableRes val computerChoice: Int,

    @ColumnInfo(name = "result")
    val result: Result?,

    @ColumnInfo(name = "date")
    val date: Date,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) {
    enum class Result(val value: String) {
        WIN("win"),
        DRAW("draw"),
        LOSS("loss")
    }

    enum class Choice {
        ROCK,
        PAPER,
        SCISSORS
    }
}