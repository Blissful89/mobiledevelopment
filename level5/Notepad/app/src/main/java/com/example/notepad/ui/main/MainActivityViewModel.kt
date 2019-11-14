package com.example.notepad.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notepad.repository.NoteRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val noteRepository = NoteRepository(application.applicationContext)

    val note = noteRepository.getNotepad()

}
