package com.vancoding.notetakingapp.di

import dagger.Component
import com.vancoding.notetakingapp.viewmodel.NoteViewModel
import com.vancoding.notetakingapp.viewmodel.NoteListViewModel

@Component(modules = [AppModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: NoteListViewModel)
}