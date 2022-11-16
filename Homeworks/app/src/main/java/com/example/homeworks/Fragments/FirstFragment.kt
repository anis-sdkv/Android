package com.example.homeworks.Fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.text.Editable
import android.text.TextWatcher
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

    private val airPlaneModeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            onAirPlaneModeChanged()
            Toast.makeText(context, "Aiplane mode changed!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFirstBinding.bind(view)
        _alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
        setListeners()
        setWatchers()

        requireActivity().registerReceiver(
            airPlaneModeReceiver,
            IntentFilter("android.intent.action.AIRPLANE_MODE")
        )

        onAirPlaneModeChanged()
    }

    private fun onAirPlaneModeChanged() {
        with(binding) {
            val value = !isAirplaneModeOn()
            etTitle.isEnabled = value
            etShortMessage.isEnabled = value
            cbExpandedMessage.isEnabled = value
            etTime.isEnabled = value
            btnCancel.isEnabled = value
            btnTime.isEnabled = value
            if (cbExpandedMessage.isChecked)
                etExpandedMessage.isEnabled = value
            if (!etTitle.text.isEmpty() && !etShortMessage.text.isEmpty() && !etTime.text.isEmpty())
                btnShow.isEnabled = value
        }

    }

    private fun isAirplaneModeOn(): Boolean {
        return Settings.System.getInt(
            requireContext().contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) != 0
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

    private fun setWatchers() {
        with(binding) {
            etTitle.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    s: CharSequence, start: Int, before: Int,
                    count: Int
                ) {
                    // TODO Auto-generated method stub
                    if (!(s.toString() == "") && etShortMessage.text.length > 0 && etTime.text.length > 0) {
                        btnShow.setEnabled(true)
                    } else {
                        btnShow.setEnabled(false)
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                }
            })
            etShortMessage.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    s: CharSequence, start: Int, before: Int,
                    count: Int
                ) {
                    if (!(s.toString() == "") && etTitle.text.length > 0 && etTime.text.length > 0) {
                        btnShow.setEnabled(true)
                    } else {
                        btnShow.setEnabled(false)
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                }
            })
            etTime.addTextChangedListener(object : TextWatcher {
                override fun onTextChanged(
                    s: CharSequence, start: Int, before: Int,
                    count: Int
                ) {
                    // TODO Auto-generated method stub
                    if (!(s.toString() == "") && etShortMessage.text.length > 0 && etTitle.text.length > 0) {
                        btnShow.setEnabled(true)
                    } else {
                        btnShow.setEnabled(false)
                    }
                }

                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int,
                    after: Int
                ) {
                }

                override fun afterTextChanged(s: Editable) {
                }
            })
        }
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