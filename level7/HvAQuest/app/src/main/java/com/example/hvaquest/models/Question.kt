package com.example.hvaquest.models

data class Question(
    var question: String,
    var choices: List<String>,
    var correctAnswer: String,
    var clue: Int
)
