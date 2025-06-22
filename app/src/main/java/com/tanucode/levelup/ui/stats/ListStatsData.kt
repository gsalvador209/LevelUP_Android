package com.tanucode.levelup.ui.stats

data class ListStatsData(
    val listId : Long,
    val listName : String,
    val cells: List<HeatmapCell>
)