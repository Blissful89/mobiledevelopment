package com.example.lendahand.dao

import androidx.room.*
import com.example.lendahand.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table")
    suspend fun getAllTasks(): List<Task>

    @Insert
    suspend fun insertTask(task: Task)

    @Insert
    suspend fun insertAllTasks(list: List<Task>?)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()
}
