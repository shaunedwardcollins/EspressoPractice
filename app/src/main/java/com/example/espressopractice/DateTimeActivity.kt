package com.example.espressopractice

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import timber.log.Timber
import java.util.Calendar

class DateTimeActivity : AppCompatActivity() {

    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_date_time)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.date_time_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnOpenDatePicker).setOnClickListener {
            showDatePicker()
        }

        findViewById<Button>(R.id.btnOpenTimePicker).setOnClickListener {
            showTimePicker()
        }
        
        Timber.d("DateTimeActivity created")
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            tvSelectedDate.text = "${getString(R.string.label_date)}$date"
            Timber.d("Date selected: $date")
        }, year, month, day).show()
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val time = String.format("%02d:%02d", selectedHour, selectedMinute)
            tvSelectedTime.text = "${getString(R.string.label_time)}$time"
            Timber.d("Time selected: $time")
        }, hour, minute, true).show()
    }
}
