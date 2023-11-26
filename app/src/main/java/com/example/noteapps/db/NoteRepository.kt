package com.example.noteapps.db

import androidx.lifecycle.LiveData

class NoteRepository(private val notesDao: NotesDAO) {
    val allNotes: LiveData<List<Notes>> = notesDao.getAllNotes()

    suspend fun insert(note: Notes){
        notesDao.insert(note)
    }

    suspend fun update(note: Notes){
        notesDao.update(note)
    }

    suspend fun delete(note: Notes){
        notesDao.delete(note)
    }
}