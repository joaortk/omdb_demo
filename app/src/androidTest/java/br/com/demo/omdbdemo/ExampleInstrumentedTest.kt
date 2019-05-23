package br.com.demo.omdbdemo

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.feature.detail.view.MovieDetailActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleInstrumentedTest {


    @get:Rule
    var activityRule: ActivityTestRule<MovieDetailActivity> =
        object : ActivityTestRule<MovieDetailActivity>(MovieDetailActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                return Intent(targetContext, MovieDetailActivity::class.java).apply {
                    putExtra(MovieDetailActivity.MOVIE_ARG, Movie(title = "Avengers: Endgame*", imdbId = "tt4154796"))
                }
            }
        }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = activityRule.activity
        assertEquals("br.com.demo.omdbdemo", appContext.packageName)
    }
}
