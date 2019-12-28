package com.example.popularmovieskotlin.repository


import com.example.popularmovieskotlin.api.MovieApiService
import com.example.popularmovieskotlin.api.MovieApi

class MovieRepository {
    private val movieApi: MovieApiService = MovieApi.createApi()

    fun getMoviesFromYear(year: String) = movieApi.getMoviesFromYear(year)
}
