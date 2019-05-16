package br.com.demo.omdbdemo.data.api

import br.com.demo.omdbdemo.data.response.MovieResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface OmdbAPI {
    @POST("consultant-mobile-backend/v1/login")
    fun search(
        @Query("s") title: String,
        @Query("type") page: String?,
        @Query("y") year: String?
    ): Call<List<MovieResponse>>
}