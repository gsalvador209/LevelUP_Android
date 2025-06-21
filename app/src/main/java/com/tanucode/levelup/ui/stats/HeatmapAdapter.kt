package com.tanucode.levelup.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tanucode.levelup.R

class HeatmapAdapter : ListAdapter<HeatmapCell, HeatmapViewHolder>(DIFF_CALLBACK) {
    private var maxCount : Int = 1 //Conteo base
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeatmapViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_heatmap_cell,parent,false)
        return HeatmapViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HeatmapViewHolder,
        position: Int
    ) {
        val cell = getItem(position)
        holder.bind(cell,maxCount)
    }

    override fun submitList(list: List<HeatmapCell>?) {
        maxCount = list?.maxOfOrNull { it.count} ?: 1
        super.submitList(list)
    }

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HeatmapCell>() {
            override fun areItemsTheSame(
                oldItem: HeatmapCell,
                newItem: HeatmapCell
            ): Boolean = oldItem.date == newItem.date


            override fun areContentsTheSame(
                oldItem: HeatmapCell,
                newItem: HeatmapCell
            ): Boolean = oldItem == newItem

        }
    }




}