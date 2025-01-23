package com.vancoding.notetakingapp.di

import android.app.Application
import com.vancoding.core.repository.NoteRepository
import com.vancoding.notetakingapp.framework.RoomNoteDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(application: Application) = NoteRepository(RoomNoteDataSource(application))
}