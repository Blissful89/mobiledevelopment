package com.example.popularmovieskotlin.api

import com.example.popularmovieskotlin.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val KEY = "de0b48de6ba0b53788ef68a830f09172"

interface MovieApiService {
    @GET("discover/movie?api_key=${KEY}&language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&")
    fun getMoviesFromYear(@Query("primary_release_year") year: String): Call<ApiResponse>
}