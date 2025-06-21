package com.tanucode.levelup.ui.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tanucode.levelup.databinding.FragmentCalendarBinding
import com.tanucode.levelup.ui.calendar.week.WeekCalendarAdapter
import com.tanucode.levelup.ui.calendar.week.WeekCalendarViewModel
import com.tanucode.levelup.util.Constants


class CalendarFragment : Fragment() {

    private var _binding : FragmentCalendarBinding? = null
    private val binding  get() = _binding!!

    private lateinit var weekViewModel : WeekCalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weekViewModel = ViewModelProvider(this)[WeekCalendarViewModel::class.java]
        val weekAdapter = WeekCalendarAdapter()
        binding.calendarWeekView.adapter = weekAdapter
        weekViewModel.events.observe(viewLifecycleOwner){events ->
            Log.d(Constants.LOGS_MESSAGE, "Found ${events.size} scheduled lists")
            weekAdapter.submitList(events)
        }


    }

}

/* for tabbar
    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabGravity="fill"
        app:tabMode="fixed">
        <com.google.android.material.tabs.TabItem
            android:id="@+id/tabDay"
            android:text="@string/day"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"/>

        <com.google.android.material.tabs.TabItem
            android:id="@+id/tabWeek"
            android:text="@string/week"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"/>

        <com.google.android.material.tabs.TabItem
            android:id="@+id/tabMonth"
            android:text="@string/month"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"/>
    </com.google.android.material.tabs.TabLayout>
 */