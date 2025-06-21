package com.tanucode.levelup.ui.stats

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tanucode.levelup.R

class HeatmapViewHolder(view:View) : RecyclerView.ViewHolder(view) {
    private val cellBg : View = view.findViewById(R.id.heatmap_cell_bg)

    fun bind(cell: HeatmapCell, maxCount : Int){
        val ratio = if (maxCount == 0) 0f else (cell.count.toFloat() / maxCount)
        val color = interpolateColor(Color.LTGRAY, Color.parseColor("#388e3C"),ratio)
        cellBg.setBackgroundColor(color)
    }

    private fun interpolateColor(colorStart: Int, colorEnd: Int, ratio: Float): Int{
        val r = Color.red(colorStart) + ((Color.red(colorEnd) - Color.red(colorStart)) * ratio).toInt()
        val g = Color.green(colorStart) + ((Color.green(colorEnd) - Color.green(colorStart)) * ratio).toInt()
        val b = Color.blue(colorStart) + ((Color.blue(colorEnd) - Color.blue(colorStart)) * ratio).toInt()
        return Color.rgb(r, g, b)

    }

}