package com.yuri.bmi_calculator

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar

import java.util.Locale

class BmiResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_bmi_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar_bmi_result)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val bmiResult = intent.getFloatExtra("BMI_RESULT", 0f)
        val tvBMIResult = findViewById<TextView>(R.id.tvBMIResult)
        tvBMIResult.text = String.format(Locale.getDefault(), "%.2f", bmiResult)

        val classification = when {
            bmiResult < 18.5f -> "UNDERWEIGHT"
            bmiResult <= 24.9f -> "NORMAL"
            bmiResult <= 29.9f -> "OVERWEIGHT"
            bmiResult <= 34.9f -> "OBESITY I"
            bmiResult <= 39.9f -> "OBESITY II"
            else -> "OBESITY III"
        }

        val tvBMIClassification = findViewById<TextView>(R.id.tvBMIClassification)
        tvBMIClassification.text = classification
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}