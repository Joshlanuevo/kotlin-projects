package com.vancoding.notetakingapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vancoding.core.data.Note
import com.vancoding.notetakingapp.databinding.ItemNoteBinding
import java.text.SimpleDateFormat
import java.util.Date

class NotesListAdapter(
    var notes: ArrayList<Note>,
    var actions: ListAction,
) : RecyclerView.Adapter<NotesListAdapter.NoteViewHolder>() {

    fun updateNotes(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
       holder.bind(notes[position])
    }

    inner class NoteViewHolder(private val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
            val sdf = SimpleDateFormat("MMM dd, HH:mm:ss")
            val resultDate = Date(note.updateTime)

            binding.apply {
                noteLayout.setOnClickListener { actions.onClick(note.id) }
                title.text = note.title
                content.text = note.content
                date.text = "Last updated: ${sdf.format(resultDate)}"
            }
        }
    }

}