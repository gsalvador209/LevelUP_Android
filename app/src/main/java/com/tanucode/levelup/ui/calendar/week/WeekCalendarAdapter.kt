package com.tanucode.levelup.ui.calendar.week

import android.util.Log
import com.alamkanak.weekview.WeekView
import com.alamkanak.weekview.WeekViewEntity
import com.tanucode.levelup.data.db.entity.TaskEntity
import com.tanucode.levelup.util.Constants
import java.util.Calendar

class WeekCalendarAdapter : WeekView.SimpleAdapter<TaskEntity>(){

    override fun onCreateEntity(
        task : TaskEntity
    ): WeekViewEntity {

        val startTime : Calendar
        val endTime : Calendar

        task.deadline.let { date ->
            startTime = Calendar.getInstance().apply {
                timeInMillis = date!!  - 1800000 //Cambiar al Date
            }
            endTime = Calendar.getInstance().apply {
                timeInMillis = date!!
            }
        }



        return WeekViewEntity.Event.Builder(task)
            .setId(task.id)
            .setTitle(task.title)
            .setStartTime(startTime)
            .setEndTime(endTime)
            .build()
    }

    override fun onEventClick(data: TaskEntity) {
        super.onEventClick(data)
        Log.d(Constants.LOGS_MESSAGE,"Click on event ${data.title}")
    }

}