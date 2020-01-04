package com.example.lendahand.api

import com.example.lendahand.model.ApiResponse
import com.example.lendahand.model.Task
import retrofit2.Call
import retrofit2.http.*

interface TaskApiService {
    @GET("api/tasks")
    fun getTasks(): Call<ApiResponse>

    @POST("api/tasks")
    fun insertTask(@Body task: Task): Call<ApiResponse>

    @DELETE("api/tasks")
    fun deleteTask(@Query("title") title: String) : Call<ApiResponse>

    @POST("api/tasks")
    fun updateTask(@Body task: Task): Call<ApiResponse>
}