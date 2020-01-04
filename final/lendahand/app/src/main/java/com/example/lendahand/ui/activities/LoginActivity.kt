package com.example.lendahand.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lendahand.R
import kotlinx.android.synthetic.main.activity_login.*

const val NAME = "Peter"
const val PASSWORD = "test"

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initViews()
    }

    private fun initViews() = btnLogin.setOnClickListener { fakeLogin() }

    private fun fakeLogin() {
        if (etName.text.toString() == NAME && etPassword.text.toString() == PASSWORD) {
            startActivity(Intent(this, MainActivity::class.java).putExtra("HOME",
                NAME
            ))
        } else Toast.makeText(this, R.string.wrong_cred, Toast.LENGTH_SHORT).show()
    }
}
