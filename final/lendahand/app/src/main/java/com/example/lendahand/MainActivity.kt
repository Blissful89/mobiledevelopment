package com.example.lendahand

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

const val NAME = "test"
const val PASSWORD = "test"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
    }

    private fun initViews(){
        btnLogin.setOnClickListener{fakeLogin()}
    }

    private fun fakeLogin(){
        if(etName.text.toString() == NAME && etPassword.text.toString() == PASSWORD){
            setContentView(R.layout.activity_home)
        } else Toast.makeText(this, R.string.wrong_cred, Toast.LENGTH_SHORT).show()
    }
}
