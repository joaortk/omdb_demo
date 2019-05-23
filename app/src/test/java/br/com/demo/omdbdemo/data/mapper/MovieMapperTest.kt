package br.com.demo.omdbdemo.data.mapper

import br.com.demo.omdbdemo.data.response.MovieResponse
import br.com.demo.omdbdemo.data.response.RatingResponse
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.model.Rating
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class MovieMapperTest {

    @Test
    fun movieListResponseToMovieList() {
        assertThat(
            MovieMapper.toMovieList(listOf(movieResponse, emptyMovieResponse))
        ).containsExactlyElementsIn(
            listOf(movie, emptyMovie)
        )
    }

    @Test
    fun movieResponseMustMapToMovie() {
        assertThat(MovieMapper.toMovie(movieResponse)).isEqualTo(movie)
    }

    //RESPONSE MOCKS
    private val ratingsResponse = listOf(
        RatingResponse("Internet Movie Database", "8.9/10"),
        RatingResponse("Rotten Tomatoes", "95%"),
        RatingResponse("Metacritic", "78/100")
    )

    private val movieResponse = MovieResponse(
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

    private val emptyMovieResponse = MovieResponse()


    //MAPPED MOCK EXPECTED RESULT
    private val ratings = listOf(
        Rating("Internet Movie Database", "8.9/10"),
        Rating("Rotten Tomatoes", "95%"),
        Rating("Metacritic", "78/100")
    )

    private val movie = Movie(
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

    private val emptyMovie = Movie()


}