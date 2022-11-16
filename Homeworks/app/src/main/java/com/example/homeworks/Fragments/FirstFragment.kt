package com.example.homeworks.Fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.homeworks.NotificationReceiver
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentFirstBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class FirstFragment : Fragment(R.layout.fragment_first) {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private var _alarmManager: AlarmManager? = null
    private val alarmManager get() = _alarmManager!!

    private var pendingsStack = Stack<Int>()

    private var requestId = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        setListeners()
        _alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
    }

    private fun setListeners() {
        with(binding) {
            cbExpandedMessage.setOnClickListener {
                etExpandedMessage.isEnabled = cbExpandedMessage.isChecked
            }

            btnTime.setOnClickListener {
                Toast.makeText(
                    requireContext(),
                    formatMillisecondsToDate(SystemClock.elapsedRealtime()),
                    Toast.LENGTH_SHORT
                ).show()
            }

            btnShow.setOnClickListener {
                setAlarm()
            }

            btnCancel.setOnClickListener {
                cancelAlarm()
            }
        }
    }

    private fun cancelAlarm() {
        with(binding) {
            with(requireContext()) {
                if (!pendingsStack.isEmpty()) {
                    val id = pendingsStack.pop()
                    val intent = Intent(this, NotificationReceiver::class.java)
                    val pendingIntent =
                        PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_ONE_SHOT)
                    alarmManager.cancel(pendingIntent)
                    Toast.makeText(this, "The last alarm was cancelled!", Toast.LENGTH_SHORT)
                        .show()
                    return
                }


                Toast.makeText(this, "No active alarms!", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun setAlarm() {
        with(binding) {
            with(requireContext()) {
                if (etTime.text.isEmpty() || etTitle.text.isEmpty() || etShortMessage.text.isEmpty()) {
                    Toast.makeText(this, "Fill in the required fields!", Toast.LENGTH_SHORT)
                        .show()
                    return
                }

                val intent = Intent(this, NotificationReceiver::class.java).apply {
                    this.putExtra("title", etTitle.text.toString())
                    this.putExtra("text", etShortMessage.text.toString())
                    this.putExtra(
                        "expanded",
                        if (cbExpandedMessage.isChecked) etExpandedMessage.text.toString()
                        else null
                    )
                }

                val executionTime =
                    System.currentTimeMillis() + etTime.text.toString().toLong() * 1000
                val pendingIntent =
                    PendingIntent.getBroadcast(
                        this,
                        requestId,
                        intent,
                        PendingIntent.FLAG_ONE_SHOT
                    )
                pendingsStack.push(requestId++)
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    executionTime,
                    pendingIntent
                )
                Toast.makeText(
                    this,
                    "Alarm set in " + etTime.text.toString() + " seconds",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatMillisecondsToDate(milliseconds: Long): String {
        val df: DateFormat = SimpleDateFormat("HH 'hours', mm 'mins,' ss 'seconds'")
        df.setTimeZone(TimeZone.getTimeZone("GMT+0"))
        return df.format(Date(milliseconds)).toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _alarmManager = null
    }

    companion object {
        const val FIRST_FRAGMENT_TAG = "FIRST_FRAGMENT_TAG"
        fun getInstance() = FirstFragment()
    }
}