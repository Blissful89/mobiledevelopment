package com.example.lendahand.repository

import com.example.lendahand.api.TaskApi
import com.example.lendahand.api.TaskApiService

class TaskRepository {
    private val taskApi: TaskApiService = TaskApi.createApi()

    fun getTasks() = taskApi.getTasks()
}
