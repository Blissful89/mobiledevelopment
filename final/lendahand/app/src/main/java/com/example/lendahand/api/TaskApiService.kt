package com.example.lendahand.api

import com.example.lendahand.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

interface TaskApiService {
    @GET("api/tasks")
    fun getTasks(): Call<ApiResponse>
}