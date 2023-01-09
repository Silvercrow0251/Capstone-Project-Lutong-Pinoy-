package com.example.dday.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.dday.Model.Notes
import com.example.dday.R
import com.example.dday.databinding.ItemNotesBinding
import com.example.dday.ui.Fragments.NotesFragmentDirections
import androidx.core.content.ContextCompat

class NotesAdapter(private val requireContext: Context, var notes: List<Notes>) :
    RecyclerView.Adapter<NotesAdapter.notesViewHolder>() {

    val generateColor: Generator = Generator()

    fun filtering(newFilterList: ArrayList<Notes>) {
        notes = newFilterList
        notifyDataSetChanged()
    }

    class notesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): notesViewHolder {
        return notesViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: notesViewHolder, position: Int) {

        holder.binding.notesCard.background.setTint(
            ContextCompat.getColor(
                requireContext,
                generateColor.getCustomColor(position)
            )
        )

        //Working
        val data = notes[position]
        holder.binding.notesTitle.text = data.title.toString()
        holder.binding.notesSubTitle.text = data.subTitle.toString()
        holder.binding.notesDate.text = data.date.toString()
        when (data.priority) {
            "1" -> holder.binding.notesPriority.setBackgroundResource(R.drawable.green_dot)
            "2" -> holder.binding.notesPriority.setBackgroundResource(R.drawable.yellow_dot)
            "3" -> holder.binding.notesPriority.setBackgroundResource(R.drawable.red_dot)
        }

        holder.binding.root.setOnClickListener {
            val action = NotesFragmentDirections.actionHomeFragmentToEditNotesfragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}

