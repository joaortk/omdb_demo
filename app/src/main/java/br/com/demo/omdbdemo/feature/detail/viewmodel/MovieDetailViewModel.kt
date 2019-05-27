package br.com.demo.omdbdemo.feature.detail.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    }


}