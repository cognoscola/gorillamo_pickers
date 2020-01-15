package com.gorillamo.pickers

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FrequencyPickerTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule<MockActivity>(MockActivity::class.java, true, false)


    @Before
    fun setUp(){

        MockActivity.layout = com.gorillamo.pickers.test.R.layout.picker_container_layout

    }
    @Test
    fun showsCorrectValueInitially() {

        MockActivity.range = 7
        MockActivity.count = 1

        activityRule.launchActivity(null)

        Thread.sleep(500)
        onView(withText("Week")).check(matches(isDisplayed()))
        assert(MockActivity.range == 7)
    }

    @Test
    fun assertAllViewsShow() {

        MockActivity.range = 7
        MockActivity.count = 1

        activityRule.launchActivity(null)

        Thread.sleep(500)
        onView(withText("Week")).check(matches(isDisplayed()))
        onView(withText("Once")).check(matches(isDisplayed()))
        onView(withText("How often?")).check(matches(isDisplayed()))
        onView(withText("Every:")).check(matches(isDisplayed()))
    }

}