package com.example.popularmovieskotlin.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.popularmovieskotlin.model.ApiResponse
import com.example.popularmovieskotlin.model.Movie
import com.example.popularmovieskotlin.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val numbersRepository = MovieRepository()
    val movies = MutableLiveData<List<Movie>>()
    val loading = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String>()

    fun getMoviesForYear(year: String) {
        loading.value = true
        numbersRepository.getMoviesFromYear(year).enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) movies.value = response.body()?.movies
                else error.value = response.errorBody().toString()
                loading.value = false
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                error.value = t.message
                loading.value = false
            }
        })
    }
}