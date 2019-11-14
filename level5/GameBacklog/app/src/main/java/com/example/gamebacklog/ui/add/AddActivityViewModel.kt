package com.example.gamebacklog.ui.add

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.gamebacklog.model.Game
import com.example.gamebacklog.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class AddActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    val game = MutableLiveData<Game?>()
    val day = MutableLiveData<Int?>()
    val month = MutableLiveData<Int?>()
    val year = MutableLiveData<Int?>()
    val error = MutableLiveData<String?>()
    val success = MutableLiveData<Boolean>()

    init {
        game.value = Game()
        day.value = 0
        month.value = 0
        year.value = 0
    }

    fun insertGame() {
        if (isGameValid()) {
            game.value!!.releaseDate =  GregorianCalendar(year.value!!, month.value!! - 1, day.value!!).time
            mainScope.launch {
                withContext(Dispatchers.IO) {
                    gameRepository.insertGame(game.value!!)
                }
                success.value = true
            }
        }
    }

    private fun isGameValid(): Boolean {
        return when {
            game.value == null -> {
                error.value = "Game must not be null"
                false
            }
            game.value!!.title.isBlank() -> {
                error.value = "Title must not be empty"
                false
            }
            game.value!!.platform.isBlank() -> {
                error.value = "Platform must not be empty"
                false
            }
            day.value!! <= 0 || day.value!! > 31 || month.value!! <= 0 || month.value!! > 12 || year.value!! <= 0 -> {
                error.value = "Date must not be empty"
                false
            }
            else -> true
        }
    }
}
