package br.com.demo.omdbdemo.feature.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import br.com.demo.omdbdemo.common.provider.ResourceProviderImpl
import br.com.demo.omdbdemo.domain.model.Movie
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
        val liveData = slot<MutableLiveData<Movie>>()
        val observer = mockk<Observer<Movie>>(relaxed = true)
        every { repository.getMovie(any(), capture(liveData)) }
                .answers {
                    liveData.captured.value = movieMock
                }
        viewModel.liveDataMediator.observeForever(observer)

        //WHEN
        viewModel.loadData("ID")

        //THEN
        verify { observer.onChanged(Movie("Avengers: Endgame")) }
    }

    @Test
    fun whenMovieReturned_mustFormatFields() {
        val liveData = slot<MutableLiveData<Movie>>()
        val observer = mockk<Observer<Movie>>(relaxed = true)
        every { repository.getMovie(any(), capture(liveData)) }
                .answers {
                    liveData.captured.value = movieMock
                }
        viewModel.liveDataMediator.observeForever(observer)

        viewModel.loadData("ID")

        assertThat(viewModel.imdbRating.get()).isEqualTo("Avaliação iMDB: 8.9")
        assertThat(viewModel.cast.get()).isEqualTo("Elenco\nRobert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth")
        assertThat(viewModel.directors.get()).isEqualTo("Direção\nAnthony Russo, Joe Russo")
        assertThat(viewModel.plot.get()).isEqualTo("Resumo\nAfter the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to undo Thanos' actions and restore order to the universe.")

    }

    private val movieMock = Movie("Avengers: Endgame")

}