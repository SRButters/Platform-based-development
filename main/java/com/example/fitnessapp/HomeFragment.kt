package com.example.fitnessapp


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    private lateinit var todaySummaryTextView: TextView
    private lateinit var stepsTextView: TextView
    private lateinit var caloriesTextView: TextView
    private lateinit var distanceTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize views
        todaySummaryTextView = view.findViewById(R.id.today_summary)
        stepsTextView = view.findViewById(R.id.steps_summary)
        caloriesTextView = view.findViewById(R.id.calories_summary)
        distanceTextView = view.findViewById(R.id.distance_summary)

        // Load daily goals and display the summary
        loadDailyGoals()

        return view
    }

    private fun loadDailyGoals() {
        val sharedPreferences = requireContext().getSharedPreferences("goals_prefs", Context.MODE_PRIVATE)

        // Retrieve the saved daily goals
        val stepsGoal = sharedPreferences.getInt("steps_goal", 0)
        val caloriesGoal = sharedPreferences.getInt("calories_goal", 0)
        val distanceGoal = sharedPreferences.getFloat("distance_goal", 0f)

        val todaySteps = 0
        val todayCalories = 0
        val todayDistance = 0

        // Set the text views with the progress
        stepsTextView.text = "Steps: $todaySteps / $stepsGoal 👟"
        caloriesTextView.text = "Calories: $todayCalories / $caloriesGoal 🔥"
        distanceTextView.text = "Distance: $todayDistance km / $distanceGoal km 🦶"

    }
}

