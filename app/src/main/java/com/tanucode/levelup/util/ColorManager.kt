package com.tanucode.levelup.util

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.tanucode.levelup.R

data class  ChipColor(
    @ColorInt val strongRes: Int,
    @ColorInt val pastelRes: Int
    )


object ColorManager {
    @SuppressLint("ResourceAsColor")
    private val palette = mapOf(
        0 to ChipColor(R.color.chip_miel_strong,   R.color.chip_miel_pastel),
        1 to ChipColor(R.color.chip_aqua_strong,   R.color.chip_aqua_pastel),
        2 to ChipColor(R.color.chip_lila_strong,   R.color.chip_lila_pastel),
        3 to ChipColor(R.color.chip_mandarina_strong, R.color.chip_mandarina_pastel),
        4 to ChipColor(R.color.chip_rosado_strong,  R.color.chip_rosado_pastel),
        5 to ChipColor(R.color.chip_cielo_strong,   R.color.chip_cielo_pastel),
        6 to ChipColor(R.color.chip_hierba_strong,  R.color.chip_hierba_pastel),
        7 to ChipColor(R.color.chip_coral_strong,   R.color.chip_coral_pastel),
        8 to ChipColor(R.color.chip_lavanda_strong, R.color.chip_lavanda_pastel),
        9 to ChipColor(R.color.chip_grafito_strong, R.color.chip_grafito_pastel)
    )

    @SuppressLint("ResourceAsColor")
    private val defaultColor = ChipColor(
        R.color.chip_grafito_strong,
        R.color.chip_grafito_pastel
    )

    /** Devuelve el color fuerte (borde, texto, ícono) para el `colorId`. */
    @ColorInt
    fun getStrongColor(context: Context, colorId: Int): Int {
        val resId = palette[colorId]?.strongRes ?: defaultColor.strongRes
        return ContextCompat.getColor(context, resId)
    }

    /** Devuelve el color pastel (fondo) para el `colorId`. */
    @ColorInt
    fun getPastelColor(context: Context, colorId: Int): Int {
        val resId = palette[colorId]?.pastelRes ?: defaultColor.pastelRes
        return ContextCompat.getColor(context, resId)
    }

}