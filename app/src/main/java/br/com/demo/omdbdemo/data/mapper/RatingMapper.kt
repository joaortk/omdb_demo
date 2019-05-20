package br.com.demo.omdbdemo.data.mapper

import br.com.demo.omdbdemo.data.response.RatingResponse
import br.com.demo.omdbdemo.domain.model.Rating

object RatingMapper {
    fun toRatingList(response: List<RatingResponse>?): List<Rating>? {
        return response?.map { toRating(it) }
    }

    private fun toRating(ratingResponse: RatingResponse): Rating {
        return Rating(ratingResponse.source, ratingResponse.value)
    }
}