package com.example.lendahand.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lendahand.model.ApiResponse
import com.example.lendahand.model.Task
import com.example.lendahand.repository.TaskRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository()
    val tasks = MutableLiveData<List<Task>>()
    val loading = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String>()

    fun getTasks() {
        loading.value = true
        taskRepository.getTasks().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) tasks.value = response.body()?.tasks
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