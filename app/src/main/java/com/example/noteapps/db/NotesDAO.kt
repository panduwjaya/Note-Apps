package com.example.noteapps.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Notes)

    @Update
    fun update(note: Notes)

    @Delete
    fun delete(note: Notes)

    @Query("SELECT * FROM noteTable ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Notes>>
}