package com.tanucode.levelup.ui.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanucode.levelup.data.repository.StatsRepository

class StatsViewModelFactory(
    private val statsRepository: StatsRepository
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(StatsViewModel::class.java)){
            return StatsViewModel(statsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class for stats")
    }

}