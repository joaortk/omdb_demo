package br.com.demo.omdbdemo.feature.home.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import br.com.demo.omdbdemo.feature.home.view.MovieItemViewModel
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val omdbRepository: OmdbRepository) : ViewModel() {
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    val liveDataMediator = MediatorLiveData<List<MovieItemViewModel>>()
    val isLoading = ObservableBoolean()

    private var timer = Timer()
    private val debounceTime: Long = 750

    init {
        liveDataMediator.addSource(moviesLiveData) { movies ->
            isLoading.set(false)
            liveDataMediator.value = movies.map { MovieItemViewModel(it) }
        }
    }

    fun searchMovie(movieTitle: String) {
        if (movieTitle.isEmpty()) {
            moviesLiveData.value = emptyList()
            return
        }
        isLoading.set(true)
        timer.cancel()
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                omdbRepository.searchMovies(movieTitle, movieLiveData = moviesLiveData)
            }
        }, debounceTime)
    }

}