package com.example.dday.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.dday.Database.NotesDatabase
import com.example.dday.Model.Notes
import com.example.dday.Repository.NotesRepository

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NotesRepository

    init {
        val dao = NotesDatabase.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)
    }

    fun addNotes(notes: Notes) {
        repository.insertNotes(notes)
    }

    fun getNotes(): LiveData<List<Notes>> {
        return repository.getAllNotes()
    }

    fun updateNotes(notes: Notes) {
        repository.updateNotes(notes)
    }

    fun deleteNotes(id: Int) {
        repository.deleteNotes(id)
    }

}

