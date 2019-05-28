package br.com.demo.omdbdemo

import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.feature.detail.view.MovieDetailActivity
import br.com.demo.omdbdemo.robot.BaseTestRobot
import org.junit.Rule
import org.junit.Test

class MovieDetailActivityTest {

    private fun assert(func: BaseTestRobot.() -> Unit) = BaseTestRobot().apply { func() }

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
    fun onDetailLoaded_mustShowFields() {
        Thread.sleep(2000)
        assert {
            isDisplayed(R.id.detail_title)
            matchText(R.id.detail_title, "Avengers: Endgame")
            isDisplayed(R.id.detail_year)
            matchText(R.id.detail_year, "2019")
            isDisplayedOnScroll(R.id.detail_poster)
            isDisplayedOnScroll(R.id.detail_cast)
            isDisplayedOnScroll(R.id.detail_directors)
            isDisplayedOnScroll(R.id.detail_plot)
            isDisplayed(R.id.detail_rating)
            matchText(R.id.detail_rating, "Avaliação iMDB: 8.9")
        }
    }
}
