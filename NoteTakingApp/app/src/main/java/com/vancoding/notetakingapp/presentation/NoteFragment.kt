package com.vancoding.notetakingapp.presentation

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.vancoding.core.data.Note
import com.vancoding.notetakingapp.databinding.FragmentNoteBinding
import com.vancoding.notetakingapp.viewmodel.NoteViewmodel

class NoteFragment : Fragment() {
    private lateinit var bindView: FragmentNoteBinding
    private lateinit var viewModel: NoteViewmodel
    private var currentNote = Note("", "", 0L, 0L)

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

        bindView.checkButton.setOnClickListener {
            if (bindView.titleView.text.toString() != "" || bindView.contentView.text.toString() != "") {
                val time = System.currentTimeMillis()
                currentNote.title = bindView.titleView.text.toString()
                currentNote.content = bindView.contentView.text.toString()
                currentNote.updateTime = time
                if (currentNote.id == 0L) {
                    currentNote.creationTime = time
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
    }

    private fun hideKeyboard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(bindView.titleView.windowToken, 0)
    }
}