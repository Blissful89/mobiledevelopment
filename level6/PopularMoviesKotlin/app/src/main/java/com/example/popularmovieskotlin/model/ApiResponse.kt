package com.example.popularmovieskotlin.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(@SerializedName("results") var movies: List<Movie>)