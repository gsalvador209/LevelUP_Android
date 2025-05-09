package com.tanucode.levelup.ui.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tanucode.levelup.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskWithListViewModel: TaskWithListViewModel
    private lateinit var taskWithListAdapter: TaskWithListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        taskWithListViewModel = ViewModelProvider(this)[TaskWithListViewModel::class.java]

        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView(){
        taskWithListAdapter = TaskWithListAdapter(){ updatedTask ->
            taskWithListViewModel.updateTask(updatedTask)
        }
        binding.taskRecyclerView.apply {
            adapter = taskWithListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupObservers(){
        taskWithListViewModel.tasksWithList.observe(
            viewLifecycleOwner,
            Observer{ tasks ->
                //Esta seccion se ejecuta cada ves que taskLiveData tiene un nuevo cvvalor
                tasks?.let{ //Si no es nullo actualizará el Recycler View
                    taskWithListAdapter.updateData(tasks)
                }
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
