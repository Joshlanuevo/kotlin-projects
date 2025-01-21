package com.vancoding.core.usecase

import com.vancoding.core.repository.NoteRepository

class GetNote(
    private val noteRepository: NoteRepository,
) {
    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}