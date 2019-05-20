package br.com.demo.omdbdemo.data.api

import br.com.demo.omdbdemo.data.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbAPI {
    @GET
    fun getSearch(
        @Query("s") title: String,
        @Query("type") type: String?,
        @Query("y") year: String?
    ): Call<List<MovieResponse>>

    @GET
    fun getMovie(
        @Query("i") id: String
    ): Call<MovieResponse>
}