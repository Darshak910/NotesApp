package com.example.roomdemo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val noteRepository: NotesRepository
    val readAllNotes : LiveData<List<Notes>>

    init {
        val notesDao = NotesDatabase.getDatabase(application).notesDao()
        noteRepository = NotesRepository(notesDao)
        readAllNotes = noteRepository.readAllNotes
    }

    fun addNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNote(notes)
        }
    }

    fun updateNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(notes)
        }
    }

    fun deleteNote(notes: Notes) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(notes)
        }
    }
}