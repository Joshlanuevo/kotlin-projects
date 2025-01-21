package com.vancoding.core.usecase

import com.vancoding.core.data.Note
import com.vancoding.core.repository.NoteRepository

class RemoveNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(note: Note) = noteRepository.remove(note)
}