package br.com.demo.omdbdemo.domain.repository

import androidx.lifecycle.MutableLiveData
import br.com.demo.omdbdemo.domain.model.Movie

interface OmdbRepository {
    fun searchMovies(
            title: String,
            type: String?,
            year: String?
    ): MutableLiveData<List<Movie>>

    fun getMovie(
            id: String
    ): MutableLiveData<Movie>
}