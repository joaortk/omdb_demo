package br.com.demo.omdbdemo.domain.repository.impl

import androidx.lifecycle.Observer
import br.com.demo.omdbdemo.data.api.OmdbAPI
import br.com.demo.omdbdemo.data.response.MovieResponse
import br.com.demo.omdbdemo.data.response.RatingResponse
import br.com.demo.omdbdemo.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(RobolectricTestRunner::class)
class OmdbRepositoryImplTest {

    private lateinit var repository: OmdbRepositoryImpl

    private val api = mockk<OmdbAPI>()

    @Before
    fun setup() {
        repository = OmdbRepositoryImpl(api)
    }

    @Test
    fun onSearchMoviesSuccess_mustReturnMovies() {
        setupApiSearchSuccess()
        val observer = mockk<Observer<List<Movie>>>(relaxed = true)
        val liveData = repository.searchMovies(title = "Avengers: Endgame", type = "MOVIE", year = "2019")
        liveData.observeForever(observer)
        verify { observer.onChanged(any()) }
    }

    @Test
    fun onSearchMoviesError_mustReturnNull() {
        setupSearchApiFail()
        val observer = mockk<Observer<List<Movie>>>(relaxed = true)
        val liveData = repository.searchMovies(title = "Avengers: Endgame", type = "MOVIE", year = "2019")
        liveData.observeForever(observer)
        verify { observer.onChanged(null) }
    }

    @Test
    fun onGetMovieSuccess_mustReturnMovie() {
        setupApiGetMovieSuccess()
        val observer = mockk<Observer<Movie>>(relaxed = true)
        val liveData = repository.getMovie(id = "MOVIE_ID")
        liveData.observeForever(observer)
        verify { observer.onChanged(any()) }
    }

    private fun setupApiGetMovieSuccess() {
        val callbackSlot = slot<Callback<MovieResponse>>()
        val call = mockk<Call<MovieResponse>>()
        every { call.enqueue(capture(callbackSlot)) }.answers {
            callbackSlot.captured.onResponse(
                call,
                Response.success(movieResponse)
            )
        }
        every { api.getMovie(any()) }.returns(call)
    }

    @Test
    fun onGetMovieError_mustReturnNull() {
        setupGetMovieApiFail()
        val observer = mockk<Observer<Movie>>(relaxed = true)
        val liveData = repository.getMovie(id = "MOVIE_ID")
        liveData.observeForever(observer)
        verify { observer.onChanged(null) }
    }

    private fun setupSearchApiFail() {
        val callbackSlot = slot<Callback<List<MovieResponse>>>()
        val call = mockk<Call<List<MovieResponse>>>()
        every { call.enqueue(capture(callbackSlot)) }.answers {
            callbackSlot.captured.onFailure(
                call,
                Exception()
            )
        }
        every { api.getSearch(any(), any(), any()) }.returns(call)
    }

    private fun setupGetMovieApiFail() {
        val callbackSlot = slot<Callback<MovieResponse>>()
        val call = mockk<Call<MovieResponse>>()
        every { call.enqueue(capture(callbackSlot)) }.answers {
            callbackSlot.captured.onFailure(
                call,
                Exception()
            )
        }
        every { api.getMovie(any()) }.returns(call)
    }


    private fun setupApiSearchSuccess() {
        val callbackSlot = slot<Callback<List<MovieResponse>>>()
        val call = mockk<Call<List<MovieResponse>>>()
        every { call.enqueue(capture(callbackSlot)) }.answers {
            callbackSlot.captured.onResponse(
                call,
                Response.success(listOf(movieResponse))
            )
        }
        every { api.getSearch(any(), any(), any()) }.returns(call)
    }


    private val ratingsResponse = listOf(
        RatingResponse("Internet Movie Database", "8.9/10"),
        RatingResponse("Rotten Tomatoes", "95%"),
        RatingResponse("Metacritic", "78/100")
    )

    private val movieResponse =
        MovieResponse(
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
            "https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg",
            ratingsResponse,
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