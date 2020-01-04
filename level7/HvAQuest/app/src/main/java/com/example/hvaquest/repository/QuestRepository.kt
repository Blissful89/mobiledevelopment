package com.example.hvaquest.repository

import com.example.hvaquest.R
import com.example.hvaquest.models.Question

class QuestRepository {
    fun getHvaQuest(): List<Question> {
        return arrayListOf(
            Question(
                "Who is the co-founder of Android?",
                arrayListOf("Andy Rubin", "Larry Page & Sergey Brin", "Sundar Pichai"),
                "Andy Rubin",
                R.drawable.nicolaes_tulphuis
            ),
            Question(
                "In which language is Android written?",
                arrayListOf("Java/Kotlin", "Swift", "JavaScript"),
                "Java/Kotlin",
                R.drawable.fraijlemaborg
            ),
            Question(
                "Which method gets called after onCreate in the Activity-lifecycle?",
                arrayListOf("onStart", "onCreateView", "onResume"),
                "onStart",
                R.drawable.leeuwenburg
            ),
            Question(
                "In what year did google announce Material Design?",
                arrayListOf("2012", "2013", "2014"),
                "2014",
                R.drawable.muller_lulofshuis
            ),
            Question(
                "What is the code name of version 1.6?",
                arrayListOf("Cupcake", "Donut", "Eclair"),
                "Donut",
                R.drawable.wibauthuis
            ),
            Question(
                "Which version of Android did not run on phones?",
                arrayListOf("Android 1.0", "Android 2.0", "Android 3.0"),
                "Android 3.0",
                R.drawable.studio_hva
            ),
            Question(
                "When did Google announced Android Wear?",
                arrayListOf("2012", "2013", "2014"),
                "2014",
                R.drawable.theo_thijssenhuis
            ),
            Question(
                "Who built the first smartwatch?",
                arrayListOf("Sony, 2010", "Samsung, 2012", "LG, 2014"),
                "Sony, 2010",
                R.drawable.kohnstammhuis
            ),
            Question(
                "For what for OS was Android originally created?",
                arrayListOf("Desktop computers", "Digital cameras", "Smartphones"),
                "Digital cameras",
                R.drawable.benno_premselahuis
            ),
            Question(
                "Who is the CEO of Google?",
                arrayListOf("Andy Rubin", "Larry Page & Sergey Brin", "Sundar Pichai"),
                "Sundar Pichai",
                R.drawable.koetsier_montaignehuis
            )
        )
    }
}
