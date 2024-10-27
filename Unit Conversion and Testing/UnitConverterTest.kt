package com.example.unitconverter

import org.junit.Assert.assertEquals
import org.junit.Test

class UnitConverterTest {

    @Test
    fun testCelsiusToFahrenheit() {
        val result = UnitConverter.celsiusToFahrenheit(0.0)
        assertEquals(32.0, result, 0.001)
    }
    @Test
    fun testFahrenheitToCelsius() {
        val result = UnitConverter.fahrenheitToCelsius(32.0)
        assertEquals(0.0, result, 0.001)
    }
    @Test
    fun testMetersToFeet() {
        val result = UnitConverter.metersToFeet(1.0)
        assertEquals(3.28084, result, 0.001)
    }
    @Test
    fun testFeetToMeters() {
        val result = UnitConverter.feetToMeters(3.28084)
        assertEquals(1.0, result, 0.001)
    }
    @Test
    fun testKilogramsToPounds() {
        val result = UnitConverter.kilogramsToPounds(1.0)
        assertEquals(2.20462, result, 0.001)
    }
    @Test
    fun testPoundsToKilograms() {
        val result = UnitConverter.poundsToKilograms(2.20462)
        assertEquals(1.0, result, 0.001)
    }
}
