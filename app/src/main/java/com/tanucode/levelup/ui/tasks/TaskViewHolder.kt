package com.tanucode.levelup.ui.tasks

import android.media.tv.TvTrackInfo
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.TaskElementBinding

class TaskViewHolder(
    private val binding: TaskElementBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(task: TaskEntity, onTaskCheckedChange: (TaskEntity) -> Unit){
        binding.apply {
            tvTitle.text = task.title
            tvDescription.isVisible = !task.description.isNullOrEmpty()
            tvDescription.text = task.description

            cbComplete.isChecked = task.isCompleted

            tvTitle.paint.isStrikeThruText = task.isCompleted

            //setOnClick Listener for check box
            cbComplete.setOnCheckedChangeListener { _, isChecked ->
                tvTitle.paint.isStrikeThruText = isChecked
                val updatedTask = task.copy(isCompleted = isChecked)
                onTaskCheckedChange(updatedTask)
            }
            tvDescription.text = task.description

        }
    }

}