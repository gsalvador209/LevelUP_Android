package com.tanucode.levelup.ui.main

import android.app.Dialog
import androidx.fragment.app.DialogFragment
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.DialogAddTaskBinding
import com.tanucode.levelup.ui.tasks.TaskViewModel


class AddTaskDialogFragment :  DialogFragment() {

    //Binding del fragmento
    private var _binding: DialogAddTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TaskViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddTaskBinding.inflate(layoutInflater)

        //Conseguir el ViewModel del owner de la actividad
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)
            .setTitle("Add task")
            .setPositiveButton("Agregar"){ _, _ ->
                val taskName = binding.etTaskName.text.toString()
                if(taskName.isNotBlank()){
                    viewModel.addNewTask(TaskEntity(title = taskName, listId = 0))
                }
            }
        return builder.create()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}