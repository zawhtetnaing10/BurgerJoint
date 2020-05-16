package com.zg.burgerjoint.uitests.utils

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun <T> first(matcher: Matcher<T>): Matcher<T> {
    return object :BaseMatcher<T>(){

        var isFirst : Boolean = true

        override fun describeTo(description: Description?) {
            description?.appendText(FIRST_ITEM_DESCRIPTION)
        }

        override fun matches(item: Any?): Boolean {
            if (isFirst && matcher.matches(item)) {
                isFirst = false
                return true
            }
            return false
        }
    }
}

const val FIRST_ITEM_DESCRIPTION = "Return The First Matching Item"