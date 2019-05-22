package br.com.demo.omdbdemo.feature.detail.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.demo.omdbdemo.R
import br.com.demo.omdbdemo.common.provider.ResourceProvider
import br.com.demo.omdbdemo.domain.model.Movie
import br.com.demo.omdbdemo.domain.repository.OmdbRepository
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val repository: OmdbRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    val liveDataMediator = MediatorLiveData<Movie>()
    private val movieLiveData = MutableLiveData<Movie>()
    val imdbRating = ObservableField<String>()
    val cast = ObservableField<String>()
    val directors = ObservableField<String>()
    val plot = ObservableField<String>()

    init {
        setupFormatters()
    }

    private fun setupFormatters() {
        liveDataMediator.addSource(movieLiveData) { result: Movie? ->
            liveDataMediator.value = result
            imdbRating.set(resourceProvider.getString(R.string.imdb_rating_label, result?.imdbRating ?: "-"))
            cast.set(resourceProvider.getString(R.string.cast, result?.actors ?: "-"))
            directors.set(resourceProvider.getString(R.string.directors, result?.director ?: "-"))
            plot.set(resourceProvider.getString(R.string.plot, result?.plot ?: "-"))
        }
    }

    fun loadData(id: String) {
        repository.getMovie(id, movieLiveData)
    }
}