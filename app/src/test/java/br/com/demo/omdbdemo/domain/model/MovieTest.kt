package br.com.demo.omdbdemo.domain.model

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class MovieTest {
    @Test
    fun parcelTest() {
        ParcelableTester.assertParcelable(movieMock, Movie)
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