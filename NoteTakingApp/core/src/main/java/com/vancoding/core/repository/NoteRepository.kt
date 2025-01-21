package com.vancoding.core.repository

import com.vancoding.core.data.Note

class NoteRepository(
    private val dataSource: NoteDataSource,
) {

    suspend fun addNote(note: Note) = dataSource.add(note)

    suspend fun getNote(id: Long) = dataSource.get(id)

    suspend fun getAll() = dataSource.getAll()

    suspend fun remove(note: Note) = dataSource.remove(note)
}