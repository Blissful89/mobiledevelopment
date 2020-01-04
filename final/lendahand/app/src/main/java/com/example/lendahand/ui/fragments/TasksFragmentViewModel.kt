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

    fun insertTask(task: Task){
        loading.value = true
        onResponse(taskRepository.insertTaskOnline(task),Mode.NO_POST.string)
    }

    fun deleteTask(task: Task) {
        loading.value = true
        onResponse(taskRepository.deleteTaskOnline(task),Mode.NO_DELETE.string)
    }

    fun completeTask(task: Task) {
        loading.value = true
        task.completed = true
        onResponse(taskRepository.updateTaskOnline(task),Mode.NO_UPDATE.string)
    }

    private fun getTasksOnline() {
        loading.value = true
        onResponse(taskRepository.getTasksOnline(),Mode.OFFLINE.string)
    }

    private fun onResponse(response: Call<ApiResponse>, errorMessage: String){
        response.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) tasks.value = filterCompletedTasks(response.body()!!.tasks)
                else error.value = response.errorBody().toString()
                loading.value = false
                replaceTasks()
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                error.value = errorMessage
                loading.value = false
                getTasksOffline()
            }
        })
    } companion object {
        enum class Mode(val string: String){
            NO_POST("Unable to post task!"),
            NO_DELETE("Unable to delete task!"),
            NO_UPDATE("Unable to update task!"),
            OFFLINE("Working offline!")
        }
    }

    private fun filterCompletedTasks(tasks: List<Task>) = tasks.filter{ !it.completed }

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