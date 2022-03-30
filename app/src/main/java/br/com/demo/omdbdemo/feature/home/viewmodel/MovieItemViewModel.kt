package br.com.demo.omdbdemo.feature.home.viewmodel

import br.com.demo.omdbdemo.domain.model.Movie

data class MovieItemViewModel(val movie: Movie) {
    val title = movie.title?.uppercase() ?: "Sem título"
    val photoUrl = movie.poster ?: "https://www.bauducco.com.br/wp/wordpress/wp-content/uploads/2017/09/default-placeholder-1-2.png"
}