package edu.upenn.chloele.connect4

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.*

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.Intents.times
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.matcher.IntentMatchers
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class InstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("edu.upenn.chloele.connect4", appContext.packageName)
    }

    @Rule @JvmField
    val nameActivityRule: ActivityTestRule<NameActivity> = ActivityTestRule(NameActivity::class.java)

//    @Rule @JvmField
//    val mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

//    @Rule @JvmField
//    val rActivityRule: ActivityTestRule<ResultActivity> = ActivityTestRule(ResultActivity::class.java)

    @Before
    fun init() {
        Intents.init()
    }

    @After
    fun release() {
        Intents.release()
    }

    @Test
    fun validNames() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        intended(IntentMatchers.hasComponent(MainActivity::class.java.name))
        intended(IntentMatchers.hasComponent(NameActivity::class.java.name), times(0))
    }

    @Test
    fun invalidNamesSame() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        intended(IntentMatchers.hasComponent(MainActivity::class.java.name), times(0))
    }

    @Test
    fun invalidNamesEmpty() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText(""), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        intended(IntentMatchers.hasComponent(MainActivity::class.java.name), times(0))
    }

    @Test
    fun validNamesPutExtra() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.TextPlayer1Name)).check(matches(withText("chloe")))
        onView(withId(R.id.TextPlayer2Name)).check(matches(withText("alex")))
    }

    @Test
    fun validTurns() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.currPlayerName)).check(matches(withText("chloe")))
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.currPlayerName)).check(matches(withText("alex")))
    }

    @Test
    fun validTurnsAlternate() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.currPlayerName)).check(matches(withText("chloe")))
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.currPlayerName)).check(matches(withText("alex")))
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.currPlayerName)).check(matches(withText("chloe")))
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.currPlayerName)).check(matches(withText("alex")))
    }

    @Test
    fun player1VerticalWin() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.Winner)).check(matches(withText("chloe WON!!!")))
    }

    @Test
    fun player2VerticalWin() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.Winner)).check(matches(withText("alex WON!!!")))
    }

    @Test
    fun player1HorizontalWin() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol7)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol7)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.Winner)).check(matches(withText("chloe WON!!!")))
    }

    @Test
    fun player2HorizontalWin() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.buttonCol7)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol7)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol7)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.Winner)).check(matches(withText("alex WON!!!")))
    }

    @Test
    fun player1DiagonalWin() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol6)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.Winner)).check(matches(withText("chloe WON!!!")))
    }

    @Test
    fun player2DiagonalWin() {
        onView(withId(R.id.editPlayer1)).perform(typeText("chloe"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.editPlayer2)).perform(typeText("alex"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.buttonCol7)).perform(click())
        onView(withId(R.id.buttonCol1)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol2)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.buttonCol3)).perform(click())
        onView(withId(R.id.buttonCol6)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.buttonCol4)).perform(click())
        onView(withId(R.id.Winner)).check(matches(withText("alex WON!!!")))
    }

    @Test
    fun clearDisplaysDialog() {
        onView(withId(R.id.leaderboard)).perform(click())
        intended(IntentMatchers.hasComponent(LeaderboardActivity::class.java.name))
        onView(withId(R.id.clear)).perform(click())
        onView(withText("Are you sure you would like to reset the leaderboard?")).check(matches(isDisplayed()));
    }

    @Test
    fun clearBringsBackToMain() {
        onView(withId(R.id.leaderboard)).perform(click())
        intended(IntentMatchers.hasComponent(LeaderboardActivity::class.java.name))
        onView(withId(R.id.clear)).perform(click())
        onView(withId(android.R.id.button1)).perform(click());
        intended(IntentMatchers.hasComponent(MenuActivity::class.java.name))
    }

    @Test
    fun clearConfirms() {
        onView(withId(R.id.leaderboard)).perform(click())
        intended(IntentMatchers.hasComponent(LeaderboardActivity::class.java.name))
        onView(withId(R.id.clear)).perform(click())
        onView(withId(android.R.id.button1)).perform(click());
        intended(IntentMatchers.hasComponent(MenuActivity::class.java.name))
    }
}
