package com.vancoding.notetakingapp.di

import com.vancoding.core.repository.NoteRepository
import com.vancoding.core.usecase.AddNote
import com.vancoding.core.usecase.GetAllNotes
import com.vancoding.core.usecase.GetNote
import com.vancoding.core.usecase.GetWordCount
import com.vancoding.core.usecase.RemoveNote
import com.vancoding.notetakingapp.framework.usecase.NoteUseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {

    @Provides
    fun getUseCases(repository: NoteRepository) = NoteUseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount(),
    )
}