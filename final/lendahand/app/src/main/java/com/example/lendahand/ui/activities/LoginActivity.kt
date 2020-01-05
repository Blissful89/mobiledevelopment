package com.example.lendahand.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lendahand.R
import com.example.lendahand.model.Account
import com.example.lendahand.ui.viewmodels.AuthViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        viewModel.loggedIn.observe(this, Observer {
            if (it) goToHome(viewModel.account.value!!)
        })

        viewModel.error.observe(this, Observer {
            Snackbar.make(layoutLogin, viewModel.error.value!!, Snackbar.LENGTH_SHORT).show()
        })

        btnLogin.setOnClickListener { fakeLogin() }
    }

    private fun goToHome(account: Account) {
        startActivity(
            Intent(this, MainActivity::class.java).putExtra(
                "HOME",
                account.name
            )
        )
    }

    private fun fakeLogin() {
        val account = Account(etName.text.toString(), etPassword.text.toString())
        viewModel.login(account)
    }
}
