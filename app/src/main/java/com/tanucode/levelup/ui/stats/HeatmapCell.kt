package com.tanucode.levelup.ui.stats

import java.time.LocalDate

data class HeatmapCell(
    val date : LocalDate,
    val count : Int = 0
)