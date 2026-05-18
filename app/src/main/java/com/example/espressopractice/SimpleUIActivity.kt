package com.example.espressopractice

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.materialswitch.MaterialSwitch
import timber.log.Timber

class SimpleUIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_simple_ui)

        val etInput = findViewById<EditText>(R.id.etInput)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val cbOption = findViewById<CheckBox>(R.id.cbOption)
        val rgOptions = findViewById<RadioGroup>(R.id.rgOptions)
        val switchToggle = findViewById<MaterialSwitch>(R.id.switchToggle)
        val spinner = findViewById<Spinner>(R.id.spinnerOptions)
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        val tvResult = findViewById<TextView>(R.id.tvResult)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.simple_ui_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnSubmit.setOnClickListener {
            val input = etInput.text.toString()
            Timber.d("Submit clicked with input: $input")
            tvResult.text = "Submitted: $input"
        }

        cbOption.setOnCheckedChangeListener { _, isChecked ->
            Timber.d("Checkbox changed: $isChecked")
            tvResult.text = "Checkbox is ${if (isChecked) "Checked" else "Unchecked"}"
        }

        rgOptions.setOnCheckedChangeListener { _, checkedId ->
            val rb = findViewById<RadioButton>(checkedId)
            if (rb != null) {
                Timber.d("Radio changed: ${rb.text}")
                tvResult.text = "Selected: ${rb.text}"
            }
        }

        switchToggle.setOnCheckedChangeListener { _, isChecked ->
            Timber.d("Switch changed: $isChecked")
            tvResult.text = "Switch is ${if (isChecked) "ON" else "OFF"}"
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selected = parent?.getItemAtPosition(position).toString()
                Timber.d("Spinner selected: $selected")
                tvResult.text = "Spinner: $selected"
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Timber.d("SeekBar progress: $progress")
                tvResult.text = "Slider: $progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
