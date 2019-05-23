package br.com.demo.omdbdemo.data.mapper

import br.com.demo.omdbdemo.data.response.MovieResponse
import br.com.demo.omdbdemo.data.response.SearchResponse
import br.com.demo.omdbdemo.domain.model.Movie

object MovieMapper {

    fun toMovieList(response: SearchResponse?): List<Movie> {
        return response?.search?.map { toMovie(it) } ?: emptyList()
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
                response.poster?.replace("300","1200"),
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