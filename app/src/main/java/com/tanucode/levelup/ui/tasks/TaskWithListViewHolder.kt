package com.tanucode.levelup.ui.tasks

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity

import com.tanucode.levelup.databinding.TaskWithListElementBinding
import com.tanucode.levelup.util.ColorManager
import com.tanucode.levelup.util.IconManager
import com.tanucode.levelup.util.ListNameResolver

class TaskWithListViewHolder(
    private val binding: TaskWithListElementBinding
) : RecyclerView.ViewHolder(binding.root){

    fun bind(twl: TaskWithListEntity, onTaskCheckedChange: (TaskEntity) -> Unit){
        val task = twl.task
        val list = twl.list

        val strong = ColorManager.getStrongColor(binding.root.context, list.colorId)
        val pastel = ColorManager.getPastelColor(binding.root.context, list.colorId)


        binding.chipList.apply {
            text = ListNameResolver.resolve(binding.root.context, list)

            chipBackgroundColor = ColorStateList.valueOf(pastel)
            chipStrokeColor = ColorStateList.valueOf(strong)
            chipIcon = run {
                val res = IconManager.getIconResId(list.icon)
                if(res !=0 ) ContextCompat.getDrawable(binding.root.context, res) else null
            }
            chipIconTint = ColorStateList.valueOf(strong)

            setTextColor(strong)
        }


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