package com.tanucode.levelup.ui.lists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.data.db.entity.ListEntity
import com.tanucode.levelup.data.repository.ListRepository

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val listRepository : ListRepository by lazy {
        (application as LevelUpApp).listRepository
    }

    //LiveData con las listas
    val allLists : LiveData<List<ListEntity>> = listRepository.getAllListsLive()

}