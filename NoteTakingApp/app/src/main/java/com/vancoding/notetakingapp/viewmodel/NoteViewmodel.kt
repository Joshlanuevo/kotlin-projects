package com.vancoding.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vancoding.core.data.Note
import com.vancoding.core.repository.NoteRepository
import com.vancoding.core.usecase.AddNote
import com.vancoding.core.usecase.GetAllNotes
import com.vancoding.core.usecase.GetNote
import com.vancoding.core.usecase.RemoveNote
import com.vancoding.notetakingapp.framework.RoomNoteDataSource
import com.vancoding.notetakingapp.framework.usecase.NoteUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewmodel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val repository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = NoteUseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
    )

    val saved = MutableLiveData<Boolean>()

    fun saveNote(note: Note) {
        coroutineScope.launch {
            useCases.addNote(note)
            saved.postValue(true)
        }
    }
}