package br.com.demo.omdbdemo.data.api

import okhttp3.Interceptor
import okhttp3.Response

class OmdbApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url().newBuilder().addQueryParameter("apikey", "1abc75a6").build()
        val request = chain.request().newBuilder().url(newUrl).build()
        return chain.proceed(request)

    }
}