package com.tanucode.levelup.ui.tasks

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.tanucode.levelup.application.LevelUpApp
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.data.db.entity.TaskWithListEntity
import com.tanucode.levelup.data.repository.TaskRepository
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar

class TaskWithListViewModel(application: Application)
    : AndroidViewModel(application) { //De esta manera tengo acceso a los repos

        private val taskRepository : TaskRepository by lazy {
        (application as LevelUpApp).taskRepository
        }

        //LiveData para todas las tareas
        val tasksWithList: LiveData<List<TaskWithListEntity>> = taskRepository.getAllTasksWithList()

        //LiveData para el índice del filtro ( 0 = All, 1 = Inbox, 2 = Objectives)
        private val _filterIndex = MutableLiveData<Int>(0)
        val filterIndex: LiveData<Int> = _filterIndex

        fun filterBy(index: Int) {
            _filterIndex.value = index
        }

        // 3) LiveData combinado: tareas filtradas según _filterIndex
        private val filteredTasks: LiveData<List<TaskWithListEntity>> =
            MediatorLiveData<List<TaskWithListEntity>>().apply {
                fun update() {
                    val currentList = tasksWithList.value ?: emptyList()
                    val idx = _filterIndex.value ?: 0
                    value = when(idx) {
                        0 -> currentList                              // All
                        else -> {
                            // mapea pestaña → systemKey; ajusta nombres si cambian
                            val key = when(idx) {
                                1 -> "inbox"
                                2 -> "objectives"
                                else -> null
                            }
                            if (key == null) currentList
                            else currentList.filter { it.list.systemKey == key }
                        }
                    }
                }
                addSource(tasksWithList) { update() }
                addSource(_filterIndex) { update() }
            }

        val groupedTasks: LiveData<List<TaskSection>> = filteredTasks.map { list ->
                // Se obtiene hoy, mañana y categorías
            val today    = mutableListOf<TaskWithListEntity>()
            val tomorrow = mutableListOf<TaskWithListEntity>()
            val upcoming = mutableListOf<TaskWithListEntity>()
            val overdue  = mutableListOf<TaskWithListEntity>()

            // Punto de inicio de hoy a las 00:00
            val cal = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            val startTodayMs    = cal.timeInMillis
            val startTomorrowMs = startTodayMs + 24L * 60 * 60 * 1000
            val startAfter2Days = startTomorrowMs + 24L * 60 * 60 * 1000

            list.forEach { twl ->
                val dlMs = twl.task.deadline
                when {
                    dlMs == null               -> upcoming += twl
                    dlMs < startTodayMs        -> overdue  += twl
                    dlMs < startTomorrowMs     -> today    += twl
                    dlMs < startAfter2Days     -> tomorrow += twl
                    else                       -> upcoming += twl
                }
        }

        val sections = mutableListOf<TaskSection>()
        if (today.isNotEmpty())    { sections += TaskSection.Header("Hoy");    today.forEach   { sections += TaskSection.Item(it) } }
        if (tomorrow.isNotEmpty()) { sections += TaskSection.Header("Mañana"); tomorrow.forEach{ sections += TaskSection.Item(it) } }
        if (upcoming.isNotEmpty()) { sections += TaskSection.Header("Próximas");upcoming.forEach{ sections+= TaskSection.Item(it)} }
        if (overdue.isNotEmpty())  { sections += TaskSection.Header("Vencidas"); overdue.forEach { sections+= TaskSection.Item(it) } }
        sections
    }


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