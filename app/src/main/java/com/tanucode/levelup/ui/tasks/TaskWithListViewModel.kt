package com.tanucode.levelup.ui.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity
import com.tanucode.levelup.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskWithListViewModel(application: Application)
    : AndroidViewModel(application) { //De esta manera tengo acceso a los repos

        private val taskRepository : TaskRepository by lazy {
        (application as LevelUpApp).taskRepository
        }

    //val tasksLiveData : LiveData<List<TaskEntity>> = taskRepository.allTasks.asLiveData() //Expone a otras clases la info de las tareas
        val tasksWithList: LiveData<List<TaskWithListEntity>> = taskRepository.getAllTasksWithList()

        fun addNewTask(task: TaskEntity) {
            viewModelScope.launch {
                taskRepository.insertTask(task)
            }
        }

        fun updateTask(task: TaskEntity){
            viewModelScope.launch {
                taskRepository.updateTask(task)
            }
        }
}