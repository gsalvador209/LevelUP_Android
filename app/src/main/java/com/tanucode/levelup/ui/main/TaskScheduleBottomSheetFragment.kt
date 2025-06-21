package com.tanucode.levelup.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tanucode.levelup.databinding.FragmentTaskScheduleBottomSheetBinding
import java.util.*

class TaskScheduleBottomSheetFragment(
    private val onDateTimeSelected: (Long) -> Unit
) : BottomSheetDialogFragment() {

    private var _binding: FragmentTaskScheduleBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var selectedDateMillis: Long = 0L
    private var timeSet = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskScheduleBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nowCal = Calendar.getInstance()
        selectedDateMillis = nowCal.timeInMillis
        binding.calendarView.date = selectedDateMillis

        binding.calendarView.setOnDateChangeListener { _: CalendarView, year, month, dayOfMonth ->
            val cal = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            selectedDateMillis = cal.timeInMillis
        }

        binding.timePicker.setOnTimeChangedListener { _: TimePicker, hour, minute ->
            timeSet = true
        }


        binding.tvDateLabel.setOnClickListener {
            binding.calendarView.visibility = View.VISIBLE
            binding.timePicker.visibility = View.GONE
        }
        binding.tvTimeLabel.setOnClickListener {
            binding.calendarView.visibility = View.GONE
            binding.timePicker.visibility = View.VISIBLE
        }


        binding.btnSaveSchedule.setOnClickListener {
            val resultMillis = if (timeSet) {
                Calendar.getInstance().apply {
                    timeInMillis = selectedDateMillis
                    set(Calendar.HOUR_OF_DAY, binding.timePicker.hour)
                    set(Calendar.MINUTE, binding.timePicker.minute)
                    set(Calendar.SECOND, 0)
                }.timeInMillis
            } else {
                Calendar.getInstance().apply {
                    timeInMillis = selectedDateMillis
                    set(Calendar.HOUR_OF_DAY, 0)
                    set(Calendar.MINUTE, 0)
                    set(Calendar.SECOND, 0)
                }.timeInMillis
            }

            onDateTimeSelected(resultMillis)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
