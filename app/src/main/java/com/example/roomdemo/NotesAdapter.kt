package com.example.roomdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.data.Notes

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.MyViewHolder>() {

    private var notesList = emptyList<Notes>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNoteItem: TextView = itemView.findViewById(R.id.textViewNoteItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.note_item, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = notesList[position]

        holder.textViewNoteItem.text = currentItem.title

        holder.itemView.findViewById<ConstraintLayout>(R.id.noteItemConstraint).setOnClickListener {
            // Action to navigate to the UpdateFragment
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun setData(note: List<Notes>) {
        this.notesList = note
        notifyDataSetChanged()
    }
}