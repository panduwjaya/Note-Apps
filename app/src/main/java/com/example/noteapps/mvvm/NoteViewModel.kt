package com.example.noteapps.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.noteapps.db.NoteRepository
import com.example.noteapps.db.Notes
import com.example.noteapps.db.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application){

    val allNotes: LiveData<List<Notes>>
    private val repository: NoteRepository

    init {
        val dao = NotesDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun addNote(note: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

    fun updateNote(note: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.update(note)
    }

    fun deleteNote(note: Notes) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }
}