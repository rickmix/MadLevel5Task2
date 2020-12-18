package com.example.madlevel5task2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class GameViewModel(application: Application): AndroidViewModel(application) {

    private val gameRepository = GameRepository(application.applicationContext)
    private val mainScope = CoroutineScope(Dispatchers.Main)

//    val game = gameRepository.getGames()
//    val error = MutableLiveData<String>()
    val success = MutableLiveData<Boolean>()

    val games: LiveData<List<Game>> = gameRepository.getGames()

    fun insertGame(title: String, date: Date, platform: String) {
        val newGame = Game(
            title = title,
            release = date,
            platform = platform
        )

        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(newGame)
            }

            success.value = true
        }
    }

    fun deleteGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGame(game)
            }
        }
    }

    fun deleteAllGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
        }
    }

//    private fun isNoteValid(note: Note): Boolean {
//        return when {
//            note.title.isBlank() -> {
//                error.value = "Title must no be empty"
//                false
//            }
//            else -> true
//        }
//    }
}