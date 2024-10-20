package com.example.fitnessapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GoalsAdapter(
    private val goals: List<Goal>,
    private val onCheckChanged: (Goal, Boolean) -> Unit
) : RecyclerView.Adapter<GoalsAdapter.GoalViewHolder>() {

    class GoalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val goalTextView: TextView = view.findViewById(R.id.goal_text)
        val goalCheckBox: CheckBox = view.findViewById(R.id.goal_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.goal_item, parent, false)
        return GoalViewHolder(view)
    }

    override fun onBindViewHolder(holder: GoalViewHolder, position: Int) {
        val goal = goals[position]
        holder.goalTextView.text = goal.title
        holder.goalCheckBox.isChecked = goal.isCompleted
        holder.goalCheckBox.setOnCheckedChangeListener { _, isChecked ->
            onCheckChanged(goal, isChecked)
        }
    }

    override fun getItemCount(): Int {
        return goals.size
    }
}
