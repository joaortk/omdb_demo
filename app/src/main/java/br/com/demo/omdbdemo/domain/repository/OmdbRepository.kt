package br.com.demo.omdbdemo.domain.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.demo.omdbdemo.domain.model.Movie

interface OmdbRepository {
    fun searchMovies(
        title: String,
        type: String?,
        year: String?,
        movieLiveData: MutableLiveData<List<Movie>>
    ): LiveData<List<Movie>>

    fun getMovie(
        id: String,
        movieLiveData: MutableLiveData<Movie>
    )
}