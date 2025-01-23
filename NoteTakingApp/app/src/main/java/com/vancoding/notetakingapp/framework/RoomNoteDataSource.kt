package com.vancoding.notetakingapp.framework

import android.content.Context
import com.vancoding.core.data.Note
import com.vancoding.core.repository.NoteDataSource
import com.vancoding.notetakingapp.framework.database.NoteDatabase
import com.vancoding.notetakingapp.framework.database.NoteEntity

class RoomNoteDataSource(context: Context): NoteDataSource {
    private val noteDao = NoteDatabase.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}