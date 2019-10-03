package com.example.rockpaperscissors.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.rockpaperscissors.R
import com.example.rockpaperscissors.model.Game
import com.example.rockpaperscissors.repository.GameRepository

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

const val HISTORY_ACTIVITY = 1337

class MainActivity : AppCompatActivity() {
    private lateinit var gameRepository: GameRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        gameRepository = GameRepository(this)
        initViews()
    }

    private fun initViews() {
        ivRock.setOnClickListener { onSelectGameInput(Game.Choice.ROCK) }
        ivPaper.setOnClickListener { onSelectGameInput(Game.Choice.PAPER) }
        ivScissors.setOnClickListener { onSelectGameInput(Game.Choice.SCISSORS) }
        updateStatistics()
    }

    private fun onSelectGameInput(choice: Game.Choice) {
        val computer = Game.Choice.values()[(Game.Choice.values().indices).random()]
        val game = createGame(choice, computer)

        showResult(game)
        insertGameIntoDatabase(game)
    }

    private fun createGame(choice: Game.Choice, computer: Game.Choice): Game {
        return Game(
            getDrawableId(choice),
            getDrawableId(computer),
            getResult(choice, computer),
            Date()
        )
    }

    private fun getDrawableId(choice: Game.Choice): Int {
        return when (choice) {
            Game.Choice.ROCK -> R.drawable.rock
            Game.Choice.PAPER -> R.drawable.paper
            Game.Choice.SCISSORS -> R.drawable.scissors
        }
    }

    private fun getResult(a: Game.Choice, b: Game.Choice): Game.Result {
        return if (a == b) Game.Result.DRAW
        else if (
            a == Game.Choice.ROCK && b == Game.Choice.SCISSORS ||
            a == Game.Choice.PAPER && b == Game.Choice.ROCK ||
            a == Game.Choice.SCISSORS && b == Game.Choice.PAPER
        ) Game.Result.WIN
        else Game.Result.LOSS
    }

    private fun showResult(game: Game) {
        ivComputer.setImageDrawable(getDrawable(game.computerChoice))
        ivYou.setImageDrawable(getDrawable(game.playerChoice))

        when (game.result) {
            Game.Result.WIN -> tvResult.text = getString(R.string.win_text)
            Game.Result.LOSS -> tvResult.text = getString(R.string.loss_text)
            Game.Result.DRAW -> tvResult.text = getString(R.string.draw_text)
        }
    }

    private fun insertGameIntoDatabase(game: Game){
        CoroutineScope(Dispatchers.Main).launch{
            withContext(Dispatchers.IO){
                gameRepository.insertGame(game)
            }
            updateStatistics()
        }
    }

    private fun updateStatistics() {
        CoroutineScope(Dispatchers.Main).launch {
            var win = 0
            var draw = 0
            var lose = 0
            withContext(Dispatchers.IO) {
                win = gameRepository.getNumberOfWins()
                draw = gameRepository.getNumberOfDraws()
                lose = gameRepository.getNumberOfLosses()
            }
            tvStatistics.text = getString(R.string.statistics,win,draw,lose)
        }

    }

    private fun historyActivity() {
        startActivityForResult(Intent(this,HistoryActivity::class.java), HISTORY_ACTIVITY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                HISTORY_ACTIVITY ->  updateStatistics()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_open_history -> {
                historyActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
