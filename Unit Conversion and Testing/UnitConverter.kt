package com.example.unitconverter

object UnitConverter {
    fun celsiusToFahrenheit(celsius: Double): Double = celsius * 9 / 5 + 32
    fun fahrenheitToCelsius(fahrenheit: Double): Double = (fahrenheit - 32) * 5 / 9
    fun metersToFeet(meters: Double): Double = meters * 3.28084
    fun feetToMeters(feet: Double): Double = feet / 3.28084
    fun kilogramsToPounds(kilograms: Double): Double = kilograms * 2.20462
    fun poundsToKilograms(pounds: Double): Double = pounds / 2.20462
}
