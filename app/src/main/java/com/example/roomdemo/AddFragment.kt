package com.example.roomdemo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdemo.data.Notes
import com.example.roomdemo.data.NotesViewModel
import com.example.roomdemo.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding

    private lateinit var mNotesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)

        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        binding.buttonAddSave.setOnClickListener {
            addNote()
        }

        return binding.root
    }

    private fun addNote() {
        val title = binding.editTextAddTitle.text.toString()
        val note = binding.editTextAddNote.text.toString()

        if (inputCheck(title, note)) {
            // Create a note
            val notes = Notes(0, title, note)

            // Save note to the database
            mNotesViewModel.addNote(notes)

            Toast.makeText(requireContext(),"Saved!", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(requireContext(),"No note was saved", Toast.LENGTH_LONG).show()
        }

        // Navigating back to the List Fragment
        findNavController().navigate(R.id.action_addFragment_to_listFragment)
    }

    private fun inputCheck(title: String, note: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }
}