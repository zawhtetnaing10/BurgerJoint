//package com.zg.burgerjoint.uitests.burgerlist
//
//import android.content.Intent
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.action.ViewActions.*
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.contrib.RecyclerViewActions
//import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
//import androidx.test.espresso.matcher.ViewMatchers.withId
//import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
//import androidx.test.rule.ActivityTestRule
//import com.zg.burgerjoint.R
//import com.zg.burgerjoint.activities.MainActivity
//import com.zg.burgerjoint.viewholders.BurgerViewHolder
//import org.junit.Before
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4ClassRunner::class)
//class GoToBurgerDetailsSuccessTest {
//    private val activityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)
//
//    @Before
//    open fun setUp() {
//        activityTestRule.launchActivity(Intent())
//    }
//
//    @Test
//    fun tapOnBurger_navigateToBurgerDetails() {
//        onView(withId(R.id.rvBurgerList))
//            .perform(RecyclerViewActions.actionOnItemAtPosition<BurgerViewHolder>(0, click()))
//        onView(withId(R.id.tvDescription))
//            .check(matches(isDisplayed()))
//    }
//}