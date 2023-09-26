package com.ivanojok.movieapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieInterface {

    @GET("/3/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey:String): MovieResponse

}