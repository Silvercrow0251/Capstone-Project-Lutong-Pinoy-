package com.example.dday.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.dday.Model.Notes
import com.example.dday.R
import com.example.dday.ViewModel.NotesViewModel
import com.example.dday.databinding.FragmentCreateNotesBinding
import java.text.SimpleDateFormat
import java.util.*
import androidx.navigation.Navigation
import com.example.dday.ui.Adapter.Generator

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    private val viewModel: NotesViewModel by viewModels()
    val generateColor: Generator = Generator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pGreen.setOnClickListener {
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority = "1"
        }
        binding.pYellow.setOnClickListener {
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
            priority = "2"
        }
        binding.pRed.setOnClickListener {
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority = "3"
        }

        return binding.root
    }

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val currentDate = SimpleDateFormat("MMMM d, yyyy").format(Date())
        if (title.isNotEmpty() && subTitle.isNotEmpty() && notes.isNotEmpty()) {
            val newNote =
                Notes(
                    null,
                    title = title,
                    subTitle = subTitle,
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }else if (title.isEmpty() && subTitle.isNotEmpty() && notes.isNotEmpty()){
            val newNote =
                Notes(
                    null,
                    title = "-",
                    subTitle = subTitle,
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }else if (title.isEmpty() && subTitle.isEmpty() && notes.isNotEmpty()){
            val newNote =
                Notes(
                    null,
                    title = "-",
                    subTitle = "-",
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }else if (title.isEmpty() && subTitle.isEmpty() && notes.isEmpty()){
            val newNote =
                Notes(
                    null,
                    title = "-",
                    subTitle = "-",
                    notes = "-",
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }else if (title.isNotEmpty() && subTitle.isEmpty() && notes.isNotEmpty()) {
            val newNote =
                Notes(
                    null,
                    title = title,
                    subTitle = "-",
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }
        else if (title.isNotEmpty() && subTitle.isEmpty() && notes.isEmpty()) {
            val newNote =
                Notes(
                    null,
                    title = title,
                    subTitle = "-",
                    notes = "-",
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }
        else if (title.isNotEmpty() && subTitle.isNotEmpty() && notes.isEmpty()) {
            val newNote =
                Notes(
                    null,
                    title = title,
                    subTitle = subTitle,
                    notes = "-",
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }
        else if (title.isNotEmpty() && subTitle.isEmpty() && notes.isNotEmpty()) {
            val newNote =
                Notes(
                    null,
                    title = title,
                    subTitle = "-",
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }
        else if (title.isEmpty() && subTitle.isNotEmpty() && notes.isEmpty()) {
            val newNote =
                Notes(
                    null,
                    title = "-",
                    subTitle = subTitle,
                    notes = "-",
                    date = currentDate,
                    priority = priority
                )
            viewModel.addNotes(newNote)
        }

        Toast.makeText(requireActivity(), "Note Created", Toast.LENGTH_SHORT)
            .show()
        Navigation.findNavController(it!!)
            .navigate(R.id.action_createNotesFragment_to_notesFragment)
    }

}

//Log.e("@@@@", "Pressed", )