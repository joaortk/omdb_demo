package br.com.demo.omdbdemo.domain.repository

import androidx.lifecycle.LiveData
import br.com.demo.omdbdemo.domain.model.Movie

interface OmdbRepository {
    fun searchMovies(
        title: String,
        type: String?,
        year: String?
    ): LiveData<List<Movie>>

    fun getMovie(
        id: String
    ): LiveData<Movie>
}