package com.tanucode.levelup.ui.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanucode.levelup.data.repository.StatsRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

class StatsViewModel(
    private val statsRepository: StatsRepository
) : ViewModel() {

    private val _heatmapCells = MutableLiveData<List<HeatmapCell>>()
    val heatmapCells :  LiveData<List<HeatmapCell>> = _heatmapCells //Conteo total por cada fecha

    fun loadHeatmap(listId: Long, daysBack: Int = 180) = viewModelScope.launch {
        val data = statsRepository.getHeatmapData(listId)

        //Mostar ultimos 180 días
        val today = LocalDate.now()
        val days = (0 until daysBack).map { today.minusDays(it.toLong()) }.reversed()
        val cells = days.map {day->
            HeatmapCell(day, data[day] ?: 0)
        }
        _heatmapCells.postValue(cells)

    }

}