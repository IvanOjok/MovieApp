package com.ivanojok.movieapp.data

data class MovieResponse(
    var page:Int?,
    var results: List<Movie>,
    var total_pages: Long?,
    var total_results:Long?
)

data class Movie(
    var adult:Boolean?,
    var backdrop_path:String?,
    var genre_ids: List<Int>,
    var id:Long?,
    var original_language:String?,
    var original_title:String?,
    var overview:String?,
    var popularity:Double?,
    var poster_path:String?,
    var release_date:String?,
    var title:String?,
    var video:Boolean?,
    var vote_average:Double?,
    var vote_count:Long
)
