package com.tanucode.levelup.ui.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
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

        val tasksLiveData : LiveData<List<TaskEntity>> = taskRepository.allTasks.asLiveData() //Expone a otras clases la info de las tareas


        fun addNewTask(task: TaskEntity) {
            viewModelScope.launch {
                taskRepository.insertTask(task)
            }
        }
}