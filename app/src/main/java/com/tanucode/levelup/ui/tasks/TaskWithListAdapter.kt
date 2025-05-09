package com.tanucode.levelup.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity
import com.tanucode.levelup.databinding.TaskWithListElementBinding

class TaskWithListAdapter(
    private  val onTaskCheckedChange : (TaskEntity) -> Unit
) : RecyclerView.Adapter<TaskWithListViewHolder>() {

    private var currentTasks : List<TaskWithListEntity> = emptyList()

    fun updateData(newData: List<TaskWithListEntity>){
        this.currentTasks = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): TaskWithListViewHolder {
        val binding = TaskWithListElementBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskWithListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskWithListViewHolder, position: Int) {
        holder.bind(currentTasks[position]) {updatedTask -> //La lambda declarada para actualizar
            onTaskCheckedChange(updatedTask)
        }

    }

    override fun getItemCount(): Int = currentTasks.size

}