package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application): AndroidViewModel(application) {
   private val repository: Notes_Repository
    val allNotes: LiveData<List<Notes>>

    init {
        val dao = NotesDB.getDB(application).getNoteDao()
        repository = Notes_Repository(dao)
        allNotes = repository.allNotes
    }

    fun delNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(notes)
    }
    fun insertNote(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notes)
    }
}

