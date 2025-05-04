package com.tanucode.levelup.ui.tasks

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.TaskElementBinding

class TaskViewHolder(
    private val binding: TaskElementBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(task: TaskEntity){
        binding.apply {
            tvTitle.text = task.title

            tvDescription.isVisible = !task.description.isNullOrEmpty()
            tvDescription.text = task.description


            cbComplete.isEnabled = task.isCompleted

        }
    }

}