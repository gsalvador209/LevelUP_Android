package com.tanucode.levelup.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.TaskElementBinding

class TaskAdapter() : RecyclerView.Adapter<TaskViewHolder>() {

    private var currentTasks : List<TaskEntity> = emptyList()

    fun updateData(newData: List<TaskEntity>){
        this.currentTasks = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): TaskViewHolder {
        val binding = TaskElementBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = currentTasks[position]
        holder.bind(task)

    }

    override fun getItemCount(): Int = currentTasks.size

}