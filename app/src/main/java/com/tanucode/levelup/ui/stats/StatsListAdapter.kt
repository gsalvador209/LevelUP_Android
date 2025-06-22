package com.tanucode.levelup.ui.stats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.tanucode.levelup.databinding.ItemStatsListBinding

class StatsListAdapter : ListAdapter<ListStatsData, StatsViewHolder>(DIFF){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder{
        val binding = ItemStatsListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StatsViewHolder(binding)
    }

    override  fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    companion object{
        private val DIFF = object : DiffUtil.ItemCallback<ListStatsData>(){
            override fun areItemsTheSame(
                oldItem: ListStatsData,
                newItem: ListStatsData
            ): Boolean = oldItem.listId == newItem.listId

            override fun areContentsTheSame(
                oldItem: ListStatsData,
                newItem: ListStatsData
            ): Boolean = oldItem == newItem
        }
    }


}