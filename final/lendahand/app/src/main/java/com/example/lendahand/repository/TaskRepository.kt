package com.example.lendahand.repository

import android.content.Context
import com.example.lendahand.api.TaskApi
import com.example.lendahand.api.TaskApiService
import com.example.lendahand.dao.TaskDao
import com.example.lendahand.database.TaskRoomDatabase
import com.example.lendahand.model.Account
import com.example.lendahand.model.Task

class TaskRepository(context: Context) {
    private val taskApi: TaskApiService = TaskApi.createApi()
    private val taskDao: TaskDao = TaskRoomDatabase.getDatabase(context)!!.taskDao()

    /* Api */
    fun getTasksOnline() = taskApi.getTasks()

    fun insertTaskOnline(task: Task) = taskApi.insertTask(task)

    fun deleteTaskOnline(task: Task) = taskApi.deleteTask(task.title)

    fun updateTaskOnline(task: Task) = taskApi.updateTask(task)

    fun login(account: Account) = taskApi.login(account.name,account.password)

    /* Room database */
    suspend fun getTasksOffline() = taskDao.getAllTasks()

    suspend fun insertAllTasksOffline(list: List<Task>?) = taskDao.insertAllTasks(list)

    suspend fun deleteAllTasksOffline() = taskDao.deleteAllTasks()
}
