package com.tanucode.levelup.ui.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanucode.levelup.databinding.FragmentTasksBinding
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

        taskAdapter = TaskAdapter(emptyList())
        binding.taskRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = taskAdapter
        }

        loadTasks()
    }

    private fun loadTasks() {
        lifecycleScope.launch{
            val tasks = taskViewModel.getAllTasks()
            taskAdapter.updateTasks(tasks)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
