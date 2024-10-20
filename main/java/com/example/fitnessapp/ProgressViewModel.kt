package com.example.fitnessapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProgressViewModel : ViewModel() {

    private val _dailySteps = MutableLiveData<Int>()
    private val _dailyCalories = MutableLiveData<Int>()
    private val _dailyKilometers = MutableLiveData<Double>()

    // LiveData for Daily Progress
    val dailySteps: LiveData<Int> get() = _dailySteps
    val dailyCalories: LiveData<Int> get() = _dailyCalories
    val dailyKilometers: LiveData<Double> get() = _dailyKilometers

    // Function to update progress based on step count
    fun updateProgress(steps: Int) {
        _dailySteps.value = steps
        _dailyCalories.value = calculateCalories(steps)
        _dailyKilometers.value = calculateKilometers(steps)
    }

    // Basic calculation to estimate calories burned (assumes average burn of 0.04 cal per step)
    private fun calculateCalories(steps: Int): Int {
        return (steps * 0.04).toInt()
    }

    // Basic calculation to estimate kilometers traveled (assumes average step length of 0.0008 km)
    private fun calculateKilometers(steps: Int): Double {
        return steps * 0.0008
    }
}
