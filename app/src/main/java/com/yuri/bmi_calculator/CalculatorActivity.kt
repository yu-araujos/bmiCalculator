package com.yuri.bmi_calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtWeightLabel = findViewById<TextInputLayout>(R.id.edtWeightLabel)
        val edtHeightLabel = findViewById<TextInputLayout>(R.id.edtHeightLabel)

        val edtWeight = findViewById<TextInputEditText>(R.id.edtWeight)
        val edtHeight = findViewById<TextInputEditText>(R.id.edtHeight)

        val btnCalculate = findViewById<Button>(R.id.btnCalculateBMI)

        btnCalculate.setOnClickListener {

            edtWeightLabel.error = null
            edtHeightLabel.error = null

            val weight = edtWeight.text.toString().replace(",", ".").toFloatOrNull()
            val height = edtHeight.text.toString().replace(",", ".").toFloatOrNull()

            if (weight == null || weight <= 0f) {
                edtWeightLabel.error = "Enter a valid weight"
                return@setOnClickListener
            }

            if (height == null || height <= 0f) {
                edtHeightLabel.error = "Enter a valid height"
                return@setOnClickListener
            }

            val bmiResult = weight / (height * height)

            val intent = Intent(this, BmiResultActivity::class.java)
            intent.putExtra("BMI_RESULT", bmiResult)
            startActivity(intent)

        }
    }
}