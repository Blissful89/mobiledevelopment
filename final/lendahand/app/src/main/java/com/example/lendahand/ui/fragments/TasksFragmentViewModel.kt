package com.example.lendahand.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lendahand.model.ApiResponse
import com.example.lendahand.model.Task
import com.example.lendahand.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TasksFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val taskRepository = TaskRepository(application.applicationContext)

    var tasks = MutableLiveData<List<Task>>()
    val loading = MutableLiveData<Boolean>(false)
    val error = MutableLiveData<String>()


    fun sync() {
        getTasksOnline()
    }

    private fun getTasksOnline() {
        loading.value = true
        taskRepository.getTasksOnline().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) tasks.value = response.body()?.tasks
                else error.value = response.errorBody().toString()
                loading.value = false
                replaceTasks()
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                error.value = "Working offline!"
                loading.value = false
                getTasksOffline()
            }
        })
    }


    private fun getTasksOffline() {
        ioScope.launch { tasks.postValue(taskRepository.getTasksOffline()) }
    }

    private fun replaceTasks() {
        ioScope.launch {
            taskRepository.deleteAllTasksOffline()
            taskRepository.insertAllTasksOffline(tasks.value)
        }
    }
}