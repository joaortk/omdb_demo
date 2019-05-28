package br.com.demo.omdbdemo.feature.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import br.com.demo.omdbdemo.common.provider.ResourceProviderImpl
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.model.Rating
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieDetailViewModelTest {

    lateinit var viewModel: MovieDetailViewModel

    @MockK
    private lateinit var repository: OmdbRepository

    private val resourceProvider = ResourceProviderImpl(ApplicationProvider.getApplicationContext())

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MovieDetailViewModel(repository, resourceProvider)
    }

    @Test
    fun onLoadData_mustReturnMovie() {
        //GIVEN
        val observer = mockk<Observer<Movie>>(relaxed = true)
        setupRepositoryMock()
        viewModel.liveDataMediator.observeForever(observer)

        //WHEN
        viewModel.loadDetail("SOME_ID")


        //THEN
        verify { observer.onChanged(any()) }

    }

    @Test
    fun whenMovieReturned_mustFormatFields() {
        //GIVEN
        setupRepositoryMock()
        val observer = mockk<Observer<Movie>>(relaxed = true)
        viewModel.liveDataMediator.observeForever(observer)

        //WHEN
        viewModel.loadDetail("SOME_ID")

        //THEN
        assertThat(viewModel.imdbRating.get()).isEqualTo("Avaliação iMDB: 8.9")
        assertThat(viewModel.cast.get()).isEqualTo("Elenco\nRobert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth")
        assertThat(viewModel.directors.get()).isEqualTo("Direção\nAnthony Russo, Joe Russo")
        assertThat(viewModel.plot.get()).isEqualTo("Resumo\nAfter the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos' actions and restore order to the universe.")
    }


    fun setupRepositoryMock() {
        val liveData = slot<MutableLiveData<Movie>>()
        every { repository.getMovie(any(), capture(liveData)) }
            .answers {
                liveData.captured.value = movieMock
            }
    }

    private val ratings = listOf(
        Rating("Internet Movie Database", "8.9/10"),
        Rating("Rotten Tomatoes", "95%"),
        Rating("Metacritic", "78/100")
    )
    private val movieMock = Movie(
        "Avengers: Endgame",
        "2019",
        "PG-13",
        "26 Apr 2019",
        "181 min",
        "Action, Adventure, Fantasy, Sci-Fi",
        "Anthony Russo, Joe Russo",
        "Christopher Markus, Stephen McFeely, Stan Lee (based on the Marvel comics by), Jack Kirby (based on the Marvel comics by), Jim Starlin (comic book)",
        "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth",
        "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos' actions and restore order to the universe.",
        "English, Japanese, Xhosa",
        "USA",
        "N/A",
        "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX1200.jpg",
        ratings,
        "78",
        "8.9",
        "360,133",
        "tt4154796",
        "movie",
        "N/A",
        "N/A",
        "Marvel Studios",
        "https://movies.disney.com/avengers-endgame",
        "True"
    )

}


/**
 *
@Test
fun onLoadData_mustReturnMovie() {
val observer = mockk<Observer<Movie>>(relaxed = true)
val liveData = slot<MutableLiveData<Movie>>()
every { repository.getMovie(any(), capture(liveData)) }
.answers {
liveData.captured.value = movieMock
}
viewModel.liveDataMediator.observeForever(observer)

viewModel.loadDetail("ID")

verify { observer.onChanged(Movie("Avengers: Endgame")) }
}
 *
 * */
