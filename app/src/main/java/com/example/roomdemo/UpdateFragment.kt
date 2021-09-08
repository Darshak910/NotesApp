package com.example.roomdemo

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.roomdemo.data.Notes
import com.example.roomdemo.data.NotesViewModel
import com.example.roomdemo.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding

    private lateinit var mNotesViewModel: NotesViewModel

    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        binding.editTextUpdateTitle.setText(args.currentNote.title)
        binding.editTextUpdateNote.setText(args.currentNote.note)

        binding.buttonUpdateSave.setOnClickListener {
            updateNote()
        }

        // Add the delete menu
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun updateNote() {
        // Updating the note
        val title = binding.editTextUpdateTitle.text.toString()
        val note = binding.editTextUpdateNote.text.toString()

        if (inputCheck(title, note)) {
            // Create a note
            val updateNote = Notes(args.currentNote.id, title, note)

            // Update the current note
            mNotesViewModel.updateNote(updateNote)
            Toast.makeText(requireContext(), "Updated successfully!", Toast.LENGTH_LONG)
                .show()

            // Navigate back to ListFragment
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(), "Please fill all credentials!", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun inputCheck(title: String, note: String): Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(note))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteNote()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Deleting the note...")
        builder.setMessage("Are you sure you want to delete the note?")
        builder.setPositiveButton("Yes") { _, _ ->
            mNotesViewModel.deleteNote(args.currentNote)
            Toast.makeText(requireContext(), "The note was deleted successfully!",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.create().show()
    }
}