package br.com.demo.omdbdemo.robot

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

open class BaseTestRobot {

    fun fillEditText(resId: Int, text: String): ViewInteraction =
        Espresso.onView(ViewMatchers.withId(resId)).perform(
            ViewActions.replaceText(text),
            ViewActions.closeSoftKeyboard()
        )

    fun clickButton(resId: Int): ViewInteraction =
        Espresso.onView((ViewMatchers.withId(resId))).perform(ViewActions.click())

    fun textView(resId: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(resId))

    fun matchText(viewInteraction: ViewInteraction, text: String): ViewInteraction = viewInteraction
        .check(ViewAssertions.matches(ViewMatchers.withText(text)))

    fun matchText(resId: Int, text: String): ViewInteraction = matchText(textView(resId), text)

    fun isDisplayed(resId: Int, value: Boolean? = true) = Espresso.onView(ViewMatchers.withId(resId)).check(
        ViewAssertions.matches(ViewMatchers.isDisplayed())
    )

    fun isDisplayedOnScroll(resId: Int, value: Boolean? = true) =
        Espresso.onView(ViewMatchers.withId(resId)).perform(ViewActions.scrollTo())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    

    fun isEnabled(resId: Int) =
        Espresso.onView(ViewMatchers.withId(resId)).check(ViewAssertions.matches(ViewMatchers.isEnabled()))

    fun isDisabled(resId: Int) = Espresso.onView(ViewMatchers.withId(resId)).check(
        (ViewAssertions.matches(
            Matchers.not(
                ViewMatchers.isEnabled()
            )
        ))
    )

    fun clickListItem(listRes: Int, position: Int) {
        Espresso.onData(Matchers.anything())
            .inAdapterView(Matchers.allOf(ViewMatchers.withId(listRes)))
            .atPosition(position).perform(ViewActions.click())
    }

    fun matchTextListItem(listRes: Int, position: Int, text: String) {
        Espresso.onView(matchAtPosition(position, listRes, ViewMatchers.withText(text)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun scrollAndClickButton(resId: Int) {
        Espresso.onView(ViewMatchers.withId(resId)).perform(ViewActions.scrollTo(), ViewActions.click())
    }

    fun dismissAlert() {
        Espresso.onView(ViewMatchers.withText("OK")).perform(ViewActions.click())
    }

    fun matchTextError(resId: Int, text: String) = Espresso.onView(ViewMatchers.withId(resId)).check(
        ViewAssertions.matches(
            withErrorText(text)
        )
    )

    private fun withErrorText(text: String?): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("TextInputLayout must contain text error")
            }

            override fun matchesSafely(item: View): Boolean {
                with(item as TextInputLayout) {
                    return error == text
                }
            }
        }
    }

    private fun matchAtPosition(position: Int, recyclerViewId: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : TypeSafeMatcher<View>(View::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position: $position ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                if (view !is RecyclerView) {
                    return false
                }
                val recyclerView = view.rootView.findViewById(recyclerViewId) as RecyclerView
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                    ?: return false
                if (viewHolder.itemView !is ViewGroup) return itemMatcher.matches(viewHolder.itemView)
                return ViewMatchers.hasDescendant(itemMatcher).matches(viewHolder.itemView)
            }
        }
    }

}
