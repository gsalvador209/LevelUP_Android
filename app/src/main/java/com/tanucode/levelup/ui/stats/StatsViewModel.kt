package com.tanucode.levelup.ui.stats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanucode.levelup.data.repository.ListRepository
import com.tanucode.levelup.data.repository.StatsRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId

class StatsViewModel(
    private val statsRepository: StatsRepository,
    private val listRepository: ListRepository
) : ViewModel() {

    private val _statsList = MutableLiveData<List<ListStatsData>>()
    val statsList: LiveData<List<ListStatsData>> = _statsList

    fun loadAllStats(daysBack: Int = 60) {
        viewModelScope.launch {
            val lists = listRepository.getAllLists() //Obtiene todas las listas de tarea

            val stats = lists.map { taskList->
                val map = statsRepository.getHeatmapData(taskList.id)

                val today = LocalDate.now()
                val days = (0 until daysBack).map { today.minusDays((it.toLong()))}.reversed()
                val cells = days.map { day ->
                    HeatmapCell(day, map[day] ?: 0)
                }
                ListStatsData(taskList.id, taskList.customName ?: taskList.systemKey!!, cells)
            }
            _statsList.postValue(stats)
        }
    }

}