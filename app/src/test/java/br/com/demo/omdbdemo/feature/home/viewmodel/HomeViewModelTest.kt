package br.com.demo.omdbdemo.feature.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import br.com.demo.omdbdemo.feature.home.viewmodel.HomeViewModel
import br.com.demo.omdbdemo.feature.home.viewmodel.MovieItemViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class HomeViewModelTest {

    lateinit var viewModel: HomeViewModel

    @MockK
    private lateinit var repository: OmdbRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun onLoadData_mustReturnMovie() {
        val liveData = slot<MutableLiveData<List<Movie>>>()
        val observer = mockk<Observer<List<MovieItemViewModel>>>(relaxed = true)
        every { repository.searchMovies(any(), movieLiveData = capture(liveData)) }
            .answers {
                liveData.captured.postValue(moviesMock)
            }

        viewModel.liveDataMediator.observeForever(observer)

        viewModel.searchMovie("any")

        Thread.sleep(750) // Não fazer isso.
        verify { observer.onChanged(listOf(MovieItemViewModel(Movie("Avengers: Endgame")), MovieItemViewModel(Movie("John Wick")))) }
    }

    @Test
    fun onLoadData_mustChangeLoadingState() {
        val liveData = slot<MutableLiveData<List<Movie>>>()
        every { repository.searchMovies(any(), movieLiveData = capture(liveData)) }
            .answers {
                liveData.captured.postValue(moviesMock)
            }

        var didChangeToLoading = false
        viewModel.isLoading.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                didChangeToLoading = true
            }
        })
        viewModel.searchMovie("any")

        assertThat(didChangeToLoading).isTrue()
    }

    private val moviesMock = listOf(Movie("Avengers: Endgame"), Movie("John Wick"))

}