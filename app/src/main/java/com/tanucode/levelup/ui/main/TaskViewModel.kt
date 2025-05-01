package com.tanucode.levelup.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application)
    : AndroidViewModel(application) { //De esta manera tengo acceso a los repos
        private val taskRepository : TaskRepository by lazy {
            (application as LevelUpApp).taskRepository
        }

    suspend fun getAllTasks(): List<TaskEntity>{
        return taskRepository.getAllTasks()
    }

    fun insertTask(task: TaskEntity) {
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }
    }

}