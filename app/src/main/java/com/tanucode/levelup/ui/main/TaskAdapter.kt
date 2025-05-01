package com.tanucode.levelup.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.databinding.TaskElementBinding

class TaskAdapter(
    private var tasks: List <TaskEntity> //Lista de tareas a mostrar
) : RecyclerView.Adapter<TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): TaskViewHolder {
        val binding = TaskElementBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val task = tasks[position]
        holder.bind(task)

    }

    override fun getItemCount(): Int = tasks.size

    fun updateTasks(newTasks: List <TaskEntity>){
        tasks = newTasks
        notifyDataSetChanged()
    }

}