package com.example.swipequiz

data class QuestionList(val name: String){
    companion object {
        val QUESTIONS = arrayOf(
            Question("A \'val\' and \'var\' are the same.",false),
            Question("Mobile Application Development grants 12 ECTS.",false),
            Question("A Unit in Kotlin corresponds to a void in Java.",true),
            Question("In Kotlin \'when\' replaces the \'switch\' operator in Java",true)
        )
    }
}