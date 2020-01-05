package com.example.lendahand.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lendahand.model.Account
import com.example.lendahand.model.AuthResponse
import com.example.lendahand.repository.TaskRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(application.applicationContext)

    var account = MutableLiveData<Account>()
    val error = MutableLiveData<String>()
    var loggedIn = MutableLiveData<Boolean>(false)

    fun login(account: Account) = onResponse(taskRepository.login(account))


    private fun onResponse(response: Call<AuthResponse>) {
        response.enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful) {
                    account.value = response.body()!!.account
                    loggedIn.value = true
                } else error.value = "Incorrect login"
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                error.value = "Login unsuccessful"
                loggedIn.value = false
            }
        })
    }
}