package com.projectx.homework3_7month.domain.usecase

import com.projectx.homework3_7month.domain.model.TaskModel
import com.projectx.homework3_7month.domain.repository.TaskManagerRepository

class TaskDelete(private val taskManagerRepository: TaskManagerRepository) {

    suspend fun deleteTask(taskModel: TaskModel){

        taskManagerRepository.deleteTask(taskModel)

    }
}