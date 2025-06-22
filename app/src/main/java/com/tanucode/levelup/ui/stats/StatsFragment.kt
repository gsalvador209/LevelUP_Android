package com.tanucode.levelup.ui.stats

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.R
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.ui.calendar.week.WeekCalendarAdapter

class StatsFragment : Fragment(R.layout.fragment_stats) {

    private val vm : StatsViewModel by viewModels {
        StatsViewModelFactory((requireActivity().application as LevelUpApp).statsRepository)
    }
    private  lateinit var heatmapAdapter: HeatmapAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        heatmapAdapter = HeatmapAdapter()
        val recycler = view.findViewById<RecyclerView>(R.id.heatmap_recycler)
        recycler.layoutManager = GridLayoutManager(context, 7) //7 columnas para los dias
        recycler.adapter = heatmapAdapter

        val listId = arguments?.getLong("LIST_ID") ?: 1L

        vm.heatmapCells.observe(viewLifecycleOwner){ cells ->
            heatmapAdapter.submitList(cells)
        }
        vm.loadHeatmap(listId)

    }


}