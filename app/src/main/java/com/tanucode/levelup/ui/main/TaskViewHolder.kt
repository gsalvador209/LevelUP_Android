package com.tanucode.levelup.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.TaskElementBinding

class TaskViewHolder(
    private val binding: TaskElementBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(task: TaskEntity){
        binding.apply {
            tvTitle.text = task.title
        }
    }

}