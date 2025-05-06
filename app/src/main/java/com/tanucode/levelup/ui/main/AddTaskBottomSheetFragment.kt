package com.tanucode.levelup.ui.main

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.BottomSheetAddTaskBinding
import com.tanucode.levelup.ui.tasks.TaskViewModel
import com.tanucode.levelup.util.Constants


class AddTaskBottomSheetFragment :  BottomSheetDialogFragment() {

    //Binding del fragmento
    private var _binding: BottomSheetAddTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetAddTaskBinding.inflate(inflater,container,false)
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            val taskTitle = binding.etTaskTitle.text.toString().trim()

            if(!taskTitle.isNullOrEmpty()){
                //Log.d(Constants.LOGS_MESSAGE,"Click en guardar")
                viewModel.addNewTask(TaskEntity(title = taskTitle, listId = 0))
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}