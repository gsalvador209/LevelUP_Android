package com.tanucode.levelup.ui.calendar.week

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.data.repository.TaskRepository

class WeekCalendarViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository : TaskRepository by lazy {
        (application as LevelUpApp).taskRepository
    }

    val events = taskRepository.getScheduledTasks()




    //private val _events = MutableLiveData<List<TaskEntity>>() //Cambiar el tipo para poder distinguir las listas
//


//    val taskWithList : LiveData<List<TaskWithListEntity>> = taskRepository.getAllTasksWithList()
//
//    fun addNewTask(task: TaskEntity) {
//        viewModelScope.launch {
//            taskRepository.insertTask(task)
//        }
//    }
//
//    fun updateTask(task: TaskEntity){
//        viewModelScope.launch {
//            taskRepository.updateTask(task)
//        }
//    }



}