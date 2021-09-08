package com.example.roomdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.data.NotesViewModel
import com.example.roomdemo.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var mNotesViewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentListBinding.inflate(inflater, container, false)

        // Recycler View
        val adapter = NotesAdapter()
        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initializing the NotesViewModel
        mNotesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        mNotesViewModel.readAllNotes.observe(viewLifecycleOwner, Observer { note ->
            adapter.setData(note)
        })

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }
}