package com.vancoding.notetakingapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.vancoding.notetakingapp.R
import com.vancoding.notetakingapp.databinding.FragmentListBinding
import com.vancoding.notetakingapp.viewmodel.NoteListViewModel

class ListFragment : Fragment(), ListAction {
    private lateinit var bindView: FragmentListBinding
    private lateinit var viewModel: NoteListViewModel
    private val notesListAdapter = NotesListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView = FragmentListBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoteListViewModel::class.java)

        bindView.noteListView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        bindView.addNote.setOnClickListener { goToNoteDetails() }

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(bindView.noteListView).navigate(action)
    }

    private fun observeViewModel() {
        viewModel.noteList.observe(viewLifecycleOwner) { noteList ->
            bindView.apply {
                loadingView.visibility = View.GONE
                noteListView.visibility = View.VISIBLE
                notesListAdapter.updateNotes(noteList.sortedByDescending { it.updateTime })
            }
        }
    }

    override fun onClick(id: Long) {
        goToNoteDetails(id)
    }
}