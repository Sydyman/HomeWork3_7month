package com.projectx.homework3_7month.domain.usecase

import android.os.Build
import androidx.annotation.RequiresApi
import com.projectx.homework3_7month.domain.model.TaskModel
import com.projectx.homework3_7month.domain.repository.TaskManagerRepository
import java.time.LocalDateTime

class InsertTaskUseCase(private val taskManagerRepository: TaskManagerRepository) {


    suspend fun insertTask(taskModel: TaskModel): String {

        val existingTask = taskManagerRepository.getTaskByName(taskModel.taskName)
        if (existingTask != null) {
            return "Такое уже есть"
        }


        val taskDate = taskModel.taskDate.toIntOrNull()
        val currentHour = LocalDateTime.now().hour

        if (taskDate == null || taskDate < currentHour) {
            return "Ты че в прошлом живешь"
        }
        taskManagerRepository.insertTask(taskModel)
        return "Task added successfully"
    }


}