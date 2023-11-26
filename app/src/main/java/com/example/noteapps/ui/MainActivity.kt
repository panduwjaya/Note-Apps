package com.example.noteapps.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapps.adapter.NoteClickDeleteInterface
import com.example.noteapps.adapter.NoteClickInterface
import com.example.noteapps.adapter.NoteRVAdapter
import com.example.noteapps.databinding.ActivityMainBinding
import com.example.noteapps.db.Notes
import com.example.noteapps.mvvm.NoteViewModel

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        
        binding.notesRV.layoutManager = LinearLayoutManager(this)

        // on below line we are initializing our adapter class.
        val noteRVAdapter = NoteRVAdapter(this, this, this)
        binding.notesRV.adapter = noteRVAdapter

        // on below line we are
        // initializing our view modal.
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[NoteViewModel::class.java]

        // on below line we are calling all notes method
        // from our view modal class to observer the changes on list.
        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                // on below line we are updating our list.
                noteRVAdapter.updateList(it)
            }
        })

        binding.idFAB.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onNoteClick(note: Notes) {
        // opening a new intent and passing a data to it.
        val intent = Intent(this@MainActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDescription", note.description)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Notes) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.title} Deleted", Toast.LENGTH_LONG).show()
    }
}