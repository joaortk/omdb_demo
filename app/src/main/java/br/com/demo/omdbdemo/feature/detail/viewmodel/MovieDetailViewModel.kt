package br.com.demo.omdbdemo.feature.detail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val repository: OmdbRepository) : ViewModel() {

    var movieLiveData: MutableLiveData<Movie>? = null

    fun setup(movie: Movie) {
        movieLiveData = movie.imdbId?.let { repository.getMovie(it) } ?: run { null }
        movieLiveData?.value = movie
    }

}