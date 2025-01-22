package com.vancoding.notetakingapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.vancoding.notetakingapp.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {
    private lateinit var bindView: FragmentNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindView =  FragmentNoteBinding.inflate(inflater, container, false)
        return bindView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView.checkButton.setOnClickListener { Navigation.findNavController(it).popBackStack() }
    }
}