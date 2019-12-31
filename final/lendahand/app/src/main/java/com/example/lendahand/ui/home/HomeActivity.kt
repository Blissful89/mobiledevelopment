package com.example.lendahand.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lendahand.R
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initViews()
    }

    private fun initViews() {
        btnHamburger.setOnClickListener{printSomething()}
    }

    private fun printSomething(){
        println("HAMBURGER BUTTON WERKT")
    }

}
