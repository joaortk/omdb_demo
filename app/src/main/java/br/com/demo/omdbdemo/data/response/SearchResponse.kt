package br.com.demo.omdbdemo.data.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
        @SerializedName("Search") val search: List<MovieResponse>? = null
)
