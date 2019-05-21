package br.com.demo.omdbdemo.data.mapper

import br.com.demo.omdbdemo.data.response.MovieResponse
import br.com.demo.omdbdemo.domain.model.Movie

object MovieMapper {

    fun toMovieList(response: List<MovieResponse>?): List<Movie>? {
        return response?.map { toMovie(it) } ?: run { null }
    }

    fun toMovie(response: MovieResponse?): Movie {
        return response?.let {
            Movie(
                response.title,
                response.year,
                response.rated,
                response.released,
                response.runtime,
                response.genre,
                response.director,
                response.writer,
                response.actors,
                response.plot,
                response.language,
                response.country,
                response.awards,
                response.poster,
                RatingMapper.toRatingList(response.ratings),
                response.metascore,
                response.imdbRating,
                response.imdbVotes,
                response.imdbId,
                response.type,
                response.dvd,
                response.boxOffice,
                response.production,
                response.website,
                response.response
            )
        } ?: run { Movie() }
    }
}