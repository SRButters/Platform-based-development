package com.example.fitnessapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment() {

    private lateinit var profileImage: ImageView
    private lateinit var emailInput: EditText
    private lateinit var ageInput: EditText
    private lateinit var weightInput: EditText
    private lateinit var heightInput: EditText
    private lateinit var genderSpinner: Spinner
    private lateinit var usernameInput: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize views
        profileImage = view.findViewById(R.id.profile_image)
        emailInput = view.findViewById(R.id.email_input)
        ageInput = view.findViewById(R.id.age_input)
        weightInput = view.findViewById(R.id.weight_input)
        heightInput = view.findViewById(R.id.height_input)
        genderSpinner = view.findViewById(R.id.gender_spinner)
        usernameInput = view.findViewById(R.id.username) // Username input field
        saveButton = view.findViewById(R.id.save_button)

        // Setup spinner options
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.gender_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        genderSpinner.adapter = adapter

        // Load saved profile data
        loadProfileData()

        // Set the button click listener
        saveButton.setOnClickListener {
            // Retrieve user inputs
            val username = usernameInput.text.toString()
            val email = emailInput.text.toString()
            val age = ageInput.text.toString().toIntOrNull() ?: 0
            val weight = weightInput.text.toString().toFloatOrNull() ?: 0f
            val height = heightInput.text.toString().toFloatOrNull() ?: 0f
            val gender = genderSpinner.selectedItem.toString()

            // Save data to SharedPreferences
            saveProfileData(username, email, age, weight, height, gender)

            // Notify user
            Toast.makeText(requireContext(), "Profile updated!", Toast.LENGTH_SHORT).show()

            // Load the updated profile data
            loadProfileData()
        }

        return view
    }

    // Method to save profile data to SharedPreferences
    private fun saveProfileData(username: String, email: String, age: Int, weight: Float, height: Float, gender: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("username", username) // Save username
            putString("email", email)
            putInt("age", age)
            putFloat("weight", weight)
            putFloat("height", height)
            putString("gender", gender)
            apply() // Apply changes asynchronously
        }
    }

    // Method to load profile data from SharedPreferences
    private fun loadProfileData() {
        val sharedPreferences = requireActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE)

        // Retrieve saved data
        val username = sharedPreferences.getString("username", "")
        val email = sharedPreferences.getString("email", "")
        val age = sharedPreferences.getInt("age", 0)
        val weight = sharedPreferences.getFloat("weight", 0f)
        val height = sharedPreferences.getFloat("height", 0f)
        val gender = sharedPreferences.getString("gender", "")

        // Set retrieved data to the views
        usernameInput.setText(username) // Load username
        emailInput.setText(email)
        ageInput.setText(age.toString())
        weightInput.setText(weight.toString())
        heightInput.setText(height.toString())

        // Set the gender spinner selection
        if (gender != null) {
            val position = (genderSpinner.adapter as ArrayAdapter<String>).getPosition(gender)
            genderSpinner.setSelection(position)
        }
    }
}
