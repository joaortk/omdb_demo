package br.com.demo.omdbdemo.feature.home.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val omdbRepository: OmdbRepository) : ViewModel() {
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    val liveDataMediator = MediatorLiveData<Movie>()
    val isLoading = ObservableBoolean()

    private var timer = Timer()
    private val DELAY: Long = 750

    init {
        liveDataMediator.addSource(moviesLiveData) { movies ->
            isLoading.set(false)
        }
    }

    fun searchMovie(movieTitle: String) {
        isLoading.set(true)
        timer.cancel()
        timer = Timer()
        timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        omdbRepository.searchMovies(movieTitle, movieLiveData = moviesLiveData)
                    }
                },
                DELAY
        )

    }

}