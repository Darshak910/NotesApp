package com.example.roomdemo.data

import androidx.lifecycle.LiveData

class NotesRepository(private val notesDao: NotesDao) {
    val readAllNotes : LiveData<List<Notes>> = notesDao.readAllNotes()

    suspend fun addNote(notes: Notes) {
        notesDao.addNote(notes)
    }

    suspend fun updateNote(notes: Notes) {
        notesDao.updateNote(notes)
    }

    suspend fun deleteNote(notes: Notes) {
        notesDao.deleteNote(notes)
    }
}