package com.vancoding.notetakingapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.vancoding.core.data.Note
import com.vancoding.notetakingapp.di.AppModule
import com.vancoding.notetakingapp.di.DaggerViewModelComponent
import com.vancoding.notetakingapp.framework.usecase.NoteUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteListViewModel(application: Application): AndroidViewModel(application) {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: NoteUseCases

    init {
        DaggerViewModelComponent.builder()
            .appModule(AppModule(getApplication()))
            .build()
            .inject(this)
    }

    val noteList = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val notes = useCases.getAllNotes()
            noteList.postValue(notes)
        }
    }
}