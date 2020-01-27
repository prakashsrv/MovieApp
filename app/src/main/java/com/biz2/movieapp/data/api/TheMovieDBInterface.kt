package com.biz2.movieapp.data.api

import com.pack.moviemvvm.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieDBInterface {

    @GET("movie/popular")
    fun getPopularMovie(@Query("page") page:Int,@Query("api_key")key:String ): Single<MovieResponse>
}