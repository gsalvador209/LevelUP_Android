package com.tanucode.levelup.ui.tasks

import android.content.res.ColorStateList
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.color.MaterialColors
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity

import com.tanucode.levelup.databinding.TaskWithListElementBinding
import com.tanucode.levelup.util.ColorManager
import com.tanucode.levelup.util.Constants
import com.tanucode.levelup.util.IconManager
import com.tanucode.levelup.util.ListNameResolver
import java.util.Calendar

class TaskWithListViewHolder(
    private val binding: TaskWithListElementBinding
) : RecyclerView.ViewHolder(binding.root){

    private val dateFormat = android.text.format.DateFormat.getDateFormat(binding.root.context)
    private val timeFormat = android.text.format.DateFormat.getTimeFormat(binding.root.context)


    fun bind(twl: TaskWithListEntity, onTaskCheckedChange: (TaskEntity) -> Unit){
        val task = twl.task
        val list = twl.list

        val strong = ColorManager.getStrongColor(binding.root.context, list.colorId)
        val pastel = ColorManager.getPastelColor(binding.root.context, list.colorId)

        val listChip = Chip(binding.root.context).apply{
            text = ListNameResolver.resolve(binding.root.context, list)

            chipBackgroundColor = ColorStateList.valueOf(pastel)
            chipStrokeColor = ColorStateList.valueOf(strong)
            chipIcon = IconManager.getIconResId(list.icon)
                .takeIf { it != 0 }
                ?.let { ContextCompat.getDrawable(binding.root.context, it)}

            chipIconTint = ColorStateList.valueOf(strong)
            setTextColor(strong)
            isClickable = false
        }


        //Chips para propiedades de la tarea
        val standardBg = MaterialColors.getColor(binding.root, com.google.android.material.R.attr.colorSurface)
        val onSurface = MaterialColors.getColor(binding.root, com.google.android.material.R.attr.colorOnSurface)

        val chips = mutableListOf<Chip>()

        task.deadline?.let {ts->
            Log.d(Constants.LOGS_MESSAGE,ts.toString())
            val cal = Calendar.getInstance().apply { timeInMillis = ts }

            val dateChip = Chip(binding.root.context).apply {
                text = dateFormat.format(cal.time)
                chipBackgroundColor = ColorStateList.valueOf(standardBg)
                setTextColor(onSurface)
                isCloseIconVisible = false
                isChipIconVisible  = false
            }
            chips += dateChip

            val timeChip = Chip(binding.root.context).apply {
                text = timeFormat.format(cal.time)
                chipBackgroundColor = ColorStateList.valueOf(standardBg)
                setTextColor(onSurface)
                isCloseIconVisible = false
                isChipIconVisible  = false
            }
            chips += timeChip
        }

        //Se rellena el flexbox
        binding.fbChipsContainer.removeAllViews()
        binding.fbChipsContainer.addView(listChip)
        chips.forEach { binding.fbChipsContainer.addView(it) }

        binding.apply {
            tvTitle.text = task.title
            tvDescription.isVisible = !task.description.isNullOrEmpty()
            tvDescription.text = task.description

            cbComplete.setOnCheckedChangeListener(null) //No dispara el change
            //Estado correcto
            cbComplete.isChecked = task.isCompleted
            tvTitle.paint.isStrikeThruText = task.isCompleted

            //Reactiva el listener
            cbComplete.setOnCheckedChangeListener { _, isChecked ->
                tvTitle.paint.isStrikeThruText = isChecked
                onTaskCheckedChange(task)
            }
        }
    }

}