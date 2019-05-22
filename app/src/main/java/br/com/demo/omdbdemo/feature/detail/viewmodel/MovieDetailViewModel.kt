package br.com.demo.omdbdemo.feature.detail.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val repository: OmdbRepository) : ViewModel() {

    var movie: ObservableField<Movie> = ObservableField()

    fun formatFields() {
        imdbRating.set(String.format("Avaliação iMDB: %s", movie.get()?.imdbRating))
    }

    fun loadData(id: String): MutableLiveData<Movie> {
        return repository.getMovie(id)
    }

    val imdbRating = ObservableField<String>()
}