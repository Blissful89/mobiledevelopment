package com.example.numbertriviakotlin.repository

import com.example.numbertriviakotlin.api.NumbersApi
import com.example.numbertriviakotlin.api.NumbersApiService

class NumbersRepository {
    private val numbersApi: NumbersApiService = NumbersApi.createApi()

    fun getRandomNumberTrivia() = numbersApi.getRandomNumberTrivia()
}
