package com.vancoding.core.usecase

import com.vancoding.core.data.Note
import com.vancoding.core.repository.NoteRepository

class AddNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}