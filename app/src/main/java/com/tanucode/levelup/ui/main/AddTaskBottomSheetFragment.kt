package com.tanucode.levelup.ui.main

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tanucode.levelup.R
import com.tanucode.levelup.data.db.entity.ListEntity
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.BottomSheetAddTaskBinding
import com.tanucode.levelup.domain.model.Frequency
import com.tanucode.levelup.ui.lists.ListViewModel
import com.tanucode.levelup.ui.schedule.TaskScheduleBottomSheetFragment
import com.tanucode.levelup.ui.tasks.TaskWithListViewModel
import com.tanucode.levelup.util.Constants
import com.tanucode.levelup.util.ListNameResolver
import java.util.Calendar


class AddTaskBottomSheetFragment :  BottomSheetDialogFragment() {

    //Binding del fragmento
    private var _binding: BottomSheetAddTaskBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskWithListViewModel
    private lateinit var listViewModel: ListViewModel

    private var selectedListId : Long = 0L //Id de la lista seleccionada (For default inbox)
    private var deadlineTimestamp: Long? = null
    private var descriptionVisible = false
    private var selectedFrecuency : Frequency = Frequency.NONE
    private var rrule : String? = null
    private var dtstartUtc : Long? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetAddTaskBinding.inflate(inflater,container,false)
        taskViewModel = ViewModelProvider(requireActivity())[TaskWithListViewModel::class.java]
        listViewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Selección de la lista de tareas
        listViewModel.allLists.observe(viewLifecycleOwner) { lists ->
            setupListSelector(lists)
        }

        //Validación del btn
        binding.etTaskTitle.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                binding.btnSave.isEnabled = !s.isNullOrBlank()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        //Toggle de la descripción
        binding.btnToggleDescription.setOnClickListener {
            descriptionVisible =  !descriptionVisible
            binding.etTaskDescription.visibility =
                if (descriptionVisible) View.VISIBLE else View.GONE
            binding.etTaskDescription.text = null
        }

        //Pick de la fecha limite
        binding.btnSchedule.setOnClickListener {
            //showTimePicker()
            TaskScheduleBottomSheetFragment {ts ->
                deadlineTimestamp = ts
                Log.d(Constants.LOGS_MESSAGE,"fecha elegida: $deadlineTimestamp")
                deadlineTimestamp.let {
                    binding.btnSchedule.setImageResource(R.drawable.ic_event)
                }
            }.show(parentFragmentManager, "ScheduleTask")

        }

        val freqLabels = resources.getStringArray(R.array.frequency_options)
        binding.spnFrequency.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            freqLabels
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

        binding.spnFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                v: View?,
                pos: Int,
                id: Long
            ) {
                selectedFrecuency = Frequency.entries.toTypedArray()[pos]
                binding.btnPickRecurrence.alpha = if(selectedFrecuency == Frequency.NONE) .5f else 1f
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}

        }

        binding.btnPickRecurrence.setOnClickListener {
            binding.spnFrequency.performClick()
        }


        binding.btnSave.setOnClickListener {
            val taskTitle = binding.etTaskTitle.text.toString().trim()
            val desc = binding.etTaskDescription.text.toString().trim().takeIf { it.isNotBlank() }

            if(!taskTitle.isNullOrEmpty() && selectedListId != 0L){
                //Log.d(Constants.LOGS_MESSAGE,"Click en guardar")
                taskViewModel.addNewTask(
                    TaskEntity(
                        title = taskTitle,
                        description = desc,
                        listId = selectedListId,
                        deadline = deadlineTimestamp)
                )
                dismiss()
            }
        }
    }


    private fun setupListSelector(lists : List<ListEntity>) {
        val names = lists.map { ListNameResolver.resolve(requireContext(), it) }
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            names)
        binding.actvListSelector.setAdapter(arrayAdapter)

        binding.actvListSelector.setOnClickListener {
            binding.actvListSelector.showDropDown()
        }

        //Preseleccionar inbox
        val inboxIndex = lists.indexOfFirst { it.systemKey == "inbox" } //Debería ser 1
        if (inboxIndex >= 0) {
            binding.actvListSelector.setText(names[inboxIndex],false)
            selectedListId = lists[inboxIndex].id
        }

        binding.actvListSelector.setOnItemClickListener { _, _, pos, _ ->
            selectedListId = lists[pos].id
        }
    }

//    private fun showTimePicker(){
//        val c = Calendar.getInstance()
//        DatePickerDialog(requireContext(),
//            { _, year, month, day ->
//                // 2) Hora
//                TimePickerDialog(requireContext(),
//                    { _, hour, minute ->
//                        c.set(year, month, day, hour, minute)
//                        deadlineTimestamp = c.timeInMillis
//                    },
//                    c.get(Calendar.HOUR_OF_DAY),
//                    c.get(Calendar.MINUTE),
//                    true
//                ).show()
//            },
//            c.get(Calendar.YEAR),
//            c.get(Calendar.MONTH),
//            c.get(Calendar.DAY_OF_MONTH)
//        ).show()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}