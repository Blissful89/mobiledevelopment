package com.example.numbertriviakotlin.api

import com.example.numbertriviakotlin.model.Trivia
import retrofit2.Call
import retrofit2.http.GET

interface NumbersApiService {
    // The GET method needed to retrieve a random number trivia.
    @GET("/random/trivia?json")
    fun getRandomNumberTrivia(): Call<Trivia>
}
