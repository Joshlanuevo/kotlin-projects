package com.vancoding.notetakingapp.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.vancoding.core.data.Note
import com.vancoding.notetakingapp.R
import com.vancoding.notetakingapp.databinding.FragmentNoteBinding
import com.vancoding.notetakingapp.viewmodel.NoteViewmodel

class NoteFragment : Fragment() {
    private lateinit var bindView: FragmentNoteBinding
    private lateinit var viewModel: NoteViewmodel
    private var currentNote = Note("", "", 0L, 0L)
    private var noteId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("NoteFragment", "onCreate called")
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView =  FragmentNoteBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoteViewmodel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
            if (noteId != 0L) {
                viewModel.getNote(noteId)
            }
        }

        bindView.checkButton.setOnClickListener {
            if (bindView.titleView.text.toString() != "" || bindView.contentView.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.apply {
                    title = bindView.titleView.text.toString()
                    content = bindView.contentView.text.toString()
                    updateTime = time
                    if (id == 0L) {
                        creationTime = time
                    }
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
                Navigation.findNavController(bindView.titleView).popBackStack()
            } else {
                Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner) { note ->
            note?.let {
                currentNote = it
                bindView.apply {
                    titleView.setText(it.title, TextView.BufferType.EDITABLE)
                    contentView.setText(it.content, TextView.BufferType.EDITABLE)
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(bindView.titleView.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("NoteFragment", "Options item selected: ${item.itemId}")
        when (item.itemId) {
            R.id.deleteNote -> {
                Log.d("NoteFragment", "Delete note selected")
                if (context != null && noteId != 0L) {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete Note")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes") { _, _ ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("Calncel") { _, _ -> }
                        .create()
                        .show()
                }
            }
        }
        return true
    }
}