package com.tanucode.levelup.ui.calendar

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.MonthDayBinder
import com.tanucode.levelup.R
import com.tanucode.levelup.databinding.FragmentCalendarBinding
import com.tanucode.levelup.util.Constants
import java.time.YearMonth


class CalendarFragment : Fragment() {

    private var _binding : FragmentCalendarBinding? = null
    private val binding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(0)
        val endMonth = currentMonth.plusMonths(100)
        val firstDayOfWeek = firstDayOfWeekFromLocale()

        binding.calendarView.apply {
            dayBinder = object : MonthDayBinder<DayViewContainer>{
                override fun create(view: View) = DayViewContainer(view)

                override fun bind(
                    container: DayViewContainer,
                    data: CalendarDay
                ) {
                        container.textView.text = data.date.dayOfMonth.toString()
                }
            }

            setup(startMonth,endMonth,firstDayOfWeek)
            scrollToMonth(currentMonth)
        }


    }

}