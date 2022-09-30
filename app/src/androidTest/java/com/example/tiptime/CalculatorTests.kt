package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.Matchers.containsString

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)
    private val costInput:String = "50.00"

    private fun calculate_percentage(expectedResult:String, percentOptionId:Int){
        onView(withId(percentOptionId))
            .perform(click())

        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText(costInput))
            .perform(closeSoftKeyboard())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString(expectedResult))))
    }
    @Test
    fun calculate_20_percent_tip() {
        calculate_percentage("$10.00", R.id.option_twenty_percent)
    }

    @Test
    fun calculate_18_percent_tip() {
        calculate_percentage("$9.00", R.id.option_eighteen_percent)
    }

    @Test
    fun calculate_15_percent_tip() {
        calculate_percentage("$7.50", R.id.option_fifteen_percent)
    }

    @Test
    fun calculate_15_percent_tip_with_roundup() {
        onView(withId(R.id.round_up_switch))
            .perform(click())
        calculate_percentage("$8.00", R.id.option_fifteen_percent)
    }

}