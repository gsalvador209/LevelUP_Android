package com.tanucode.levelup.ui.calendar


import android.view.View
import com.kizitonwose.calendar.view.ViewContainer
import com.tanucode.levelup.databinding.CalendarDayLayoutBinding

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
}