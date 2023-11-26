package com.example.noteapps.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "noteTable")
data class Notes(
    @ColumnInfo(name = "judul_tabel")
    val title: String,
    @ColumnInfo(name = "deskripsi_tabel")
    val description: String,
    @ColumnInfo(name = "timestamp")
    val timeStamp: String
){
    @PrimaryKey(autoGenerate = true)
    var id = 0
}
