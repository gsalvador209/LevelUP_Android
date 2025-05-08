package com.tanucode.levelup.ui.tasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.InvalidationTracker
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.FragmentTasksBinding
import com.tanucode.levelup.util.Constants
import kotlinx.coroutines.launch

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView(){
        taskAdapter = TaskAdapter(){ updatedTask ->
            taskViewModel.updateTask(updatedTask)
        }
        binding.taskRecyclerView.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers(){
        taskViewModel.tasksLiveData.observe(
            viewLifecycleOwner,
            Observer{ tasks ->
                //Esta seccion se ejecuta cada ves que taskLiveData tiene un nuevo cvvalor
                tasks?.let{ //Si no es nullo actualizará el Recycler View
                    taskAdapter.updateData(tasks)
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
