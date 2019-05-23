package br.com.demo.omdbdemo.domain.repository

import androidx.lifecycle.MutableLiveData
import br.com.demo.omdbdemo.domain.model.Movie

interface OmdbRepository {
    fun searchMovies(
        title: String,
        type: String? = null,
        year: String? = null,
        movieLiveData: MutableLiveData<List<Movie>>
    )

    fun getMovie(
        id: String,
        movieLiveData: MutableLiveData<Movie>
    )
}