package com.vancoding.notetakingapp.framework.usecase

import com.vancoding.core.usecase.AddNote
import com.vancoding.core.usecase.GetAllNotes
import com.vancoding.core.usecase.GetNote
import com.vancoding.core.usecase.GetWordCount
import com.vancoding.core.usecase.RemoveNote

data class NoteUseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val getWordCount: GetWordCount,
)