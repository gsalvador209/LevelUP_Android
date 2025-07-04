package com.tanucode.levelup.ui.tasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.tanucode.levelup.databinding.FragmentTasksBinding
import com.tanucode.levelup.util.Constants

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    private val vm: TaskWithListViewModel by viewModels()

    private lateinit var sectionAdapter: TaskSectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //vm = ViewModelProvider(this)[TaskWithListViewModel::class.java]

        //Saludo
        //binding.tvGreeting.text = "Hola, Salvador"

        //Pestañas
        val lists = listOf("All", "Inbox", "Objectives")
        lists.forEach { binding.tabLists.addTab(binding.tabLists.newTab().setText(it)) }
        binding.tabLists.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                Log.d(Constants.LOGS_MESSAGE, "Tab selected: ${tab.text}")
                vm.filterBy(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        // RecyclerView y Adapter
        sectionAdapter = TaskSectionAdapter {updatedTask ->
            vm.onTaskCheckedChange(updatedTask)
        }

        binding.rvTasks.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sectionAdapter
        }

        //Observer
        setupObservers()
    }


    private fun setupObservers(){
        vm.groupedTasks.observe(viewLifecycleOwner){ sections ->
            Log.d(Constants.LOGS_MESSAGE, "Submitting ${sections.size} sections")
            sectionAdapter.submitList(sections)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
