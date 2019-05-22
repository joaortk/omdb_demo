package br.com.demo.omdbdemo.feature.detail.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.demo.omdbdemo.R
import br.com.demo.omdbdemo.common.provider.ResourceProvider
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(private val repository: OmdbRepository,
                                               private val resourceProvider: ResourceProvider) : ViewModel() {

    var movie: ObservableField<Movie> = ObservableField()

    fun formatFields() {
        imdbRating.set(resourceProvider.getString(R.string.imdb_rating_label, movie.get()?.imdbRating ?: "-"))
    }

    fun loadData(id: String): LiveData<Movie> {
        return repository.getMovie(id)
    }

    val imdbRating = ObservableField<String>()
}