package br.com.demo.omdbdemo.feature.detail.viewmodel

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
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MovieDetailViewModelTest {

    lateinit var viewModel: MovieDetailViewModel

    @MockK
    private lateinit var repository: OmdbRepository

    private val resourceProvider = ResourceProviderImpl(ApplicationProvider.getApplicationContext())

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MovieDetailViewModel(repository, resourceProvider)
    }

    @Test
    fun onLoadData_mustReturnMovie() {
        val liveData = slot<MutableLiveData<Movie>>()
        val observer = mockk<Observer<Movie>>(relaxed = true)
        every { repository.getMovie(any(), capture(liveData)) }
                .answers {
                    liveData.captured.value = movieMock
                }
        viewModel.liveDataMediator.observeForever(observer)

        viewModel.loadData("ID")

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

        assertThat(viewModel.imdbRating.get()).isEqualTo("Avaliação iMDB: -")
    }

    private val movieMock = Movie("Avengers: Endgame")

}