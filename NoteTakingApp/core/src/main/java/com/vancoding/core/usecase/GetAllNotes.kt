package com.vancoding.core.usecase

import com.vancoding.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {
    suspend operator fun invoke() = noteRepository.getAll()
}