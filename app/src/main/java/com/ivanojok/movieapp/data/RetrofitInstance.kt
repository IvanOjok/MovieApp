package com.ivanojok.movieapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        val baseUrl = "https://api.themoviedb.org"
    }

    fun getService(): MovieInterface {
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(MovieInterface::class.java)
    }
}