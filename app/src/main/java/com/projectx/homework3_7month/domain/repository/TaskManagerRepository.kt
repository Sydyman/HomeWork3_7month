package com.projectx.homework3_7month.domain.repository

import com.projectx.homework3_7month.domain.model.TaskModel

interface TaskManagerRepository {

    suspend fun insertTask(taskModel: TaskModel)
    suspend fun getAllNotes(): List<TaskModel>
    suspend fun getTaskByName(taskName: String): TaskModel?
    suspend fun updateTask(taskModel: TaskModel)
    suspend fun deleteTask(task: TaskModel)

}