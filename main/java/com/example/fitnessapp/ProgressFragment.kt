package com.example.fitnessapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager


class ProgressFragment : Fragment(), SensorEventListener {

    private lateinit var dailyStepsTextView: TextView
    private lateinit var dailyCaloriesTextView: TextView
    private lateinit var dailyKilometersTextView: TextView

    private lateinit var sensorManager: SensorManager
    private var stepCounterSensor: Sensor? = null

    private val progressViewModel: ProgressViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_progress, container, false)

        // Initialize the TextViews
        dailyStepsTextView = view.findViewById(R.id.daily_steps)
        dailyCaloriesTextView = view.findViewById(R.id.daily_calories)
        dailyKilometersTextView = view.findViewById(R.id.daily_kilometers)

        // Initialize SensorManager
        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        stepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        // Observe LiveData from ViewModel
        progressViewModel.dailySteps.observe(viewLifecycleOwner, Observer { steps ->
            dailyStepsTextView.text = "Steps: $steps"
        })
        progressViewModel.dailyCalories.observe(viewLifecycleOwner, Observer { calories ->
            dailyCaloriesTextView.text = "Calories burned: $calories"
        })
        progressViewModel.dailyKilometers.observe(viewLifecycleOwner, Observer { kilometers ->
            dailyKilometersTextView.text = "Kilometers traveled: $kilometers km"
        })

        return view
    }

    override fun onResume() {
        super.onResume()
        // Register the sensor listener
        stepCounterSensor?.also { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onPause() {
        super.onPause()
        // Unregister the sensor listener to save battery
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {
            if (it.sensor.type == Sensor.TYPE_STEP_COUNTER) {
                // Update ViewModel with the step count from the sensor
                val steps = it.values[0].toInt() // Total steps since boot
                progressViewModel.updateProgress(steps)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // You can handle accuracy changes here if needed
    }
}
