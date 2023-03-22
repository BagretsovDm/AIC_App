package com.example.aic_app

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.aic_app.ui.MainActivity
import org.junit.Rule
import org.junit.Test

class MyTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun shouldSearchCats() {
        Espresso.onView(ViewMatchers.withId(R.id.search))
            .perform(ViewActions.click())

        Espresso.onView(ViewMatchers.withId(R.id.textInput))
            .perform(ViewActions.typeText("cats"))
            .perform(ViewActions.closeSoftKeyboard())

        Thread.sleep(5000)
    }
}
