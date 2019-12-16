package com.example.popularmovieskotlin.model

import java.util.*

data class Movie(
    var backdropImage: String,
    var posterImage: String,
    var title: String,
    var releaseDate: Date,
    var overView: String
)