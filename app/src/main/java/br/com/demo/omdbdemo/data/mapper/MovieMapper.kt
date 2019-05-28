package br.com.demo.omdbdemo.data.mapper

import br.com.demo.omdbdemo.data.response.MovieResponse
import br.com.demo.omdbdemo.data.response.SearchResponse
import br.com.demo.omdbdemo.domain.model.Movie

object MovieMapper {

    fun toMovieList(response: SearchResponse?): List<Movie> {
        return response?.search?.map { toMovie(it) } ?: emptyList()
    }

    fun toMovie(response: MovieResponse): Movie {
        return response.let {
            Movie(
                response.title,
                response.year,
                response.director,
                response.actors,
                response.plot,
                response.poster,
                response.imdbRating,
                response.imdbId
            )
        }
    }
}