package com.example.unitconverter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Celsius to Fahrenheit
        val editCelsius = findViewById<EditText>(R.id.editCelsius)
        val buttonCelsiusToFahrenheit = findViewById<Button>(R.id.buttonCelsiusToFahrenheit)
        val textviewFahrenheitResult = findViewById<TextView>(R.id.textviewFahrenheitResult)
        buttonCelsiusToFahrenheit.setOnClickListener {
            val celsius = editCelsius.text.toString().toDoubleOrNull()
            if (celsius != null) {
                val fahrenheit = UnitConverter.celsiusToFahrenheit(celsius)
                textviewFahrenheitResult.text = "Result: $fahrenheit °F"
            }
        }
        // Meters to Feet
        val editMeters = findViewById<EditText>(R.id.editMeters)
        val buttonMetersToFeet = findViewById<Button>(R.id.buttonMetersToFeet)
        val textviewFeetResult = findViewById<TextView>(R.id.textviewFeetResult)
        buttonMetersToFeet.setOnClickListener {
            val meters = editMeters.text.toString().toDoubleOrNull()
            if (meters != null) {
                val feet = UnitConverter.metersToFeet(meters)
                textviewFeetResult.text = "Result: $feet ft"
            }
        }
        // Kilograms to Pounds
        val editKilograms = findViewById<EditText>(R.id.editKilograms)
        val buttonKilogramsToPounds = findViewById<Button>(R.id.buttonKilogramsToPounds)
        val textviewPoundsResult = findViewById<TextView>(R.id.textviewPoundsResult)
        buttonKilogramsToPounds.setOnClickListener {
            val kilograms = editKilograms.text.toString().toDoubleOrNull()
            if (kilograms != null) {
                val pounds = UnitConverter.kilogramsToPounds(kilograms)
                textviewPoundsResult.text = "Result: $pounds lbs"
            }
        }
    }
}