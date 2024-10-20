package com.example.fitnessapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GoalsFragment : Fragment() {

    // For daily goals
    private lateinit var stepsGoalInput: EditText
    private lateinit var caloriesGoalInput: EditText
    private lateinit var distanceGoalInput: EditText
    private lateinit var saveGoalsButton: Button

    // For custom goals
    private lateinit var goalInput: EditText
    private lateinit var addGoalButton: Button
    private lateinit var goalsRecyclerView: RecyclerView
    private lateinit var goalsAdapter: GoalsAdapter
    private val goalsList = ArrayList<Goal>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        // Initialize daily goal views
        stepsGoalInput = view.findViewById(R.id.steps_goal_input)
        caloriesGoalInput = view.findViewById(R.id.calories_goal_input)
        distanceGoalInput = view.findViewById(R.id.distance_goal_input)
        saveGoalsButton = view.findViewById(R.id.save_goals_button)

        // Initialize custom goal views
        goalInput = view.findViewById(R.id.goal_input)
        addGoalButton = view.findViewById(R.id.add_goal_button)
        goalsRecyclerView = view.findViewById(R.id.goals_recycler_view)

        // Setup RecyclerView for custom goals
        goalsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        goalsAdapter = GoalsAdapter(goalsList) { goal, isChecked ->
            goal.isCompleted = isChecked
            saveGoals()
        }
        goalsRecyclerView.adapter = goalsAdapter

        // Load saved data
        loadDailyGoals()
        loadCustomGoals()

        // Save daily goals on button click
        saveGoalsButton.setOnClickListener {
            saveDailyGoals()
        }

        // Add new custom goal on button click
        addGoalButton.setOnClickListener {
            val newGoal = goalInput.text.toString()
            if (newGoal.isNotEmpty()) {
                goalsList.add(Goal(newGoal, false))
                goalsAdapter.notifyDataSetChanged()
                saveGoals()
                goalInput.text.clear()
            }
        }

        return view
    }

    // Load and save daily goals from SharedPreferences
    private fun loadDailyGoals() {
        val sharedPreferences = requireContext().getSharedPreferences("goals_prefs", Context.MODE_PRIVATE)
        val stepsGoal = sharedPreferences.getInt("steps_goal", 0)
        val caloriesGoal = sharedPreferences.getInt("calories_goal", 0)
        val distanceGoal = sharedPreferences.getFloat("distance_goal", 0f)

        stepsGoalInput.setText(stepsGoal.toString())
        caloriesGoalInput.setText(caloriesGoal.toString())
        distanceGoalInput.setText(distanceGoal.toString())
    }

    private fun saveDailyGoals() {
        val stepsGoal = stepsGoalInput.text.toString().toIntOrNull() ?: 0
        val caloriesGoal = caloriesGoalInput.text.toString().toIntOrNull() ?: 0
        val distanceGoal = distanceGoalInput.text.toString().toFloatOrNull() ?: 0f

        val sharedPreferences = requireContext().getSharedPreferences("goals_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putInt("steps_goal", stepsGoal)
            putInt("calories_goal", caloriesGoal)
            putFloat("distance_goal", distanceGoal)
            apply()
        }

        Toast.makeText(requireContext(), "Daily goals saved!", Toast.LENGTH_SHORT).show()
    }

    // Load and save custom goals from SharedPreferences
    private fun loadCustomGoals() {
        val sharedPreferences = requireContext().getSharedPreferences("goals_prefs", Context.MODE_PRIVATE)
        val goalsSet = sharedPreferences.getStringSet("goals_list", setOf()) ?: setOf()
        goalsList.clear()
        goalsSet.forEach { goalData ->
            val parts = goalData.split("|")
            val title = parts[0]
            val isCompleted = parts[1].toBoolean()
            goalsList.add(Goal(title, isCompleted))
        }
        goalsAdapter.notifyDataSetChanged()
    }

    private fun saveGoals() {
        val sharedPreferences = requireContext().getSharedPreferences("goals_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val goalsSet = goalsList.map { "${it.title}|${it.isCompleted}" }.toSet()
        editor.putStringSet("goals_list", goalsSet)
        editor.apply()
    }
}
