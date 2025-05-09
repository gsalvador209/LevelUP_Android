package com.tanucode.levelup.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.R
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity
import com.tanucode.levelup.databinding.TaskWithListElementBinding

class TaskSectionAdapter(
    private val onTaskChecked:(TaskEntity)-> Unit
) : ListAdapter<TaskSection, RecyclerView.ViewHolder>  (TaskWithListDiffSection()) {

    companion object{
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }

    override fun getItemViewType(psoition: Int) = when(getItem(psoition)) {
        is TaskSection.Header -> TYPE_HEADER
        is TaskSection.Item -> TYPE_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType) {
        TYPE_HEADER -> {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_section_header, parent, false)
            HeaderVH(v)
        }

        else -> {
            val binding = TaskWithListElementBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ItemVH(binding, onTaskChecked)
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        when(val section = getItem(position)) {
            is TaskSection.Header -> (holder as HeaderVH).bind(section)
            is TaskSection.Item   -> (holder as ItemVH).bind(section.data)
        }
    }


    class HeaderVH(view: View) : RecyclerView.ViewHolder(view) {
        private val tv = view.findViewById<TextView>(R.id.tvSectionHeader)
        fun bind(h: TaskSection.Header) {
            tv.text = h.title
        }
    }

    class ItemVH(
        val binding: TaskWithListElementBinding,
        val onChecked: (TaskEntity)->Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(twl: TaskWithListEntity) {
            // Reusa tu TaskWithListViewHolder
            TaskWithListViewHolder(binding).bind(twl, onChecked)
        }
    }
}
