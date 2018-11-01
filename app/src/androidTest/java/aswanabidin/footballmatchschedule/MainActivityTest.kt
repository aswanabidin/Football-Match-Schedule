package aswanabidin.footballmatchschedule

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import aswanabidin.footballmatchschedule.R.id.*
import org.junit.Test

import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {

        delay()
        onView(ViewMatchers.withId(rvLastMatch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay()
        onView(ViewMatchers.withId(rvLastMatch)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4
            )
        )

        onView(ViewMatchers.withId(rvLastMatch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, ViewActions.click())
        )
        delay()

        onView(ViewMatchers.withId(imgAway)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(imgHome)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        pressBack()
        delay()

        onView(ViewMatchers.withId(main_container)).perform(ViewActions.swipeLeft())

        delay()

        pressBack()
    }

    private fun delay() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}