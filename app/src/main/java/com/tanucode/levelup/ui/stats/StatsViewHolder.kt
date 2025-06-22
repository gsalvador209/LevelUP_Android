package com.tanucode.levelup.ui.stats

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.databinding.ItemStatsListBinding

class StatsViewHolder(
    private val binding : ItemStatsListBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val heatmapAdapter = HeatmapAdapter()

    init {
        binding.rvInnerHeatmap.apply {
            layoutManager = GridLayoutManager(
                binding.root.context,
                7, //Filas de 7 (represenatndo los días)
                GridLayoutManager.HORIZONTAL,
                false)
            adapter = heatmapAdapter
            isNestedScrollingEnabled = false
        }
    }


    fun bind(data: ListStatsData) {
        binding.tvListName.text = data.listName
        heatmapAdapter.submitList(data.cells)
    }
}