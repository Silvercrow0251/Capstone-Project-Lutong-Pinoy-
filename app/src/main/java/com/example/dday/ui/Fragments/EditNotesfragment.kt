package com.example.dday.ui.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.dday.Model.Notes
import com.example.dday.R
import com.example.dday.ViewModel.NotesViewModel
import com.example.dday.databinding.FragmentEditNotesfragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_item1.*
import kotlinx.android.synthetic.main.activity_item1.share
import kotlinx.android.synthetic.main.fragment_edit_notesfragment.*
import java.text.SimpleDateFormat
import java.util.*

class EditNotesfragment : Fragment() {

    private val newNotes by navArgs<EditNotesfragmentArgs>()
    private lateinit var binding: FragmentEditNotesfragmentBinding
    private var priority = "1"
    private val viewModel: NotesViewModel by viewModels()
    private var ctx: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesfragmentBinding.inflate(layoutInflater, container, false)

        setHasOptionsMenu(true)

        binding.editTitle.setText(newNotes.data.title)
        binding.editNotes.setText(newNotes.data.notes)
        binding.editSubTitle.setText(newNotes.data.subTitle)
        when (newNotes.data.priority) {
            "1" -> {
                binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
                priority = "1"
            }
            "2" -> {
                binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
                priority = "2"
            }
            "3" -> {
                binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
                priority = "3"
            }
        }

        binding.pGreen.setOnClickListener {
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority = "1"
            ctx = it
        }
        binding.pYellow.setOnClickListener {
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
            priority = "2"
            ctx = it
        }
        binding.pRed.setOnClickListener {
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
            priority = "3"
            ctx = it
        }

        binding.btnSaveNotes.setOnClickListener {
            ctx = it
            updateNotes(it)
        }

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    private fun updateNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()
        val notes = binding.editNotes.text.toString()
        val currentDate = SimpleDateFormat("MMMM d, yyyy").format(Date())
        if (title.isNotEmpty() && subTitle.isNotEmpty() && notes.isNotEmpty()) {
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = title,
                    subTitle = subTitle,
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }else if (title.isEmpty() && subTitle.isNotEmpty() && notes.isNotEmpty()){
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = "-",
                    subTitle = subTitle,
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }else if (title.isEmpty() && subTitle.isEmpty() && notes.isNotEmpty()){
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = "-",
                    subTitle = "-",
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }else if (title.isEmpty() && subTitle.isEmpty() && notes.isEmpty()){
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = "-",
                    subTitle = "-",
                    notes = "-",
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }else if (title.isNotEmpty() && subTitle.isEmpty() && notes.isNotEmpty()) {
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = title,
                    subTitle = "-",
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }
        else if (title.isNotEmpty() && subTitle.isEmpty() && notes.isEmpty()) {
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = title,
                    subTitle = "-",
                    notes = "-",
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }
        else if (title.isNotEmpty() && subTitle.isNotEmpty() && notes.isEmpty()) {
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = title,
                    subTitle = subTitle,
                    notes = "-",
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }
        else if (title.isNotEmpty() && subTitle.isEmpty() && notes.isNotEmpty()) {
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = title,
                    subTitle = "-",
                    notes = notes,
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }
        else if (title.isEmpty() && subTitle.isNotEmpty() && notes.isEmpty()) {
            val newNote =
                Notes(
                    newNotes.data.id,
                    title = "-",
                    subTitle = subTitle,
                    notes = "-",
                    date = currentDate,
                    priority = priority
                )
            viewModel.updateNotes(newNote)
        }
        Toast.makeText(requireActivity(), "Note Updated", Toast.LENGTH_SHORT)
            .show()
        Navigation.findNavController(it!!)
            .navigate(R.id.action_editNotesfragment_to_notesFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu_v2, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_delete) {
            val bottomSheet: BottomSheetDialog =
                BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textYes = bottomSheet.findViewById<TextView>(R.id.delete_yes)
            val textNo = bottomSheet.findViewById<TextView>(R.id.delete_no)

            textYes?.setOnClickListener {
                viewModel.deleteNotes(newNotes.data.id!!)
                bottomSheet.dismiss()
                requireActivity().onBackPressed()
//                val intent = Intent(activity, Notes::class.java)
//                startActivity(intent)
                Toast.makeText(requireActivity(), "Note Deleted", Toast.LENGTH_SHORT)
                    .show()
            }
            textNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }
        if (item.itemId == R.id.share){
//            Toast.makeText(requireActivity(), "Working", Toast.LENGTH_SHORT)
//                .show()
//                share.setOnClickListener{
                    val s = editTitle.text.toString()+"\n"+"\n"+editNotes.text.toString()
            //+editTitle.text.toString()+"\n"+"\n"
                    //Intent to share the text
                    val shareIntent = Intent()
                    shareIntent.action = Intent.ACTION_SEND
                    shareIntent.type="text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, s);
                    startActivity(Intent.createChooser(shareIntent,"Share via"))
                }
//        }
            return super.onOptionsItemSelected(item)
    }
//Share my recipes
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        share.setOnClickListener{
//            val s = editTitle.text.toString()+"\n"+editSubTitle.text.toString()+"\n"+"\n"+editNotes.text.toString()
//            //Intent to share the text
//            val shareIntent = Intent()
//            shareIntent.action = Intent.ACTION_SEND
//            shareIntent.type="text/plain"
//            shareIntent.putExtra(Intent.EXTRA_TEXT, s);
//            startActivity(Intent.createChooser(shareIntent,"Share via"))
//        }
//    }

}