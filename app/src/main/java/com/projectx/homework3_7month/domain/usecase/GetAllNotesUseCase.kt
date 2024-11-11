package com.projectx.homework3_7month.domain.usecase

import com.projectx.homework3_7month.domain.model.TaskModel
import com.projectx.homework3_7month.domain.repository.TaskManagerRepository

class GetAllNotesUseCase(
    private val taskManagerRepository: TaskManagerRepository
) {

    suspend operator fun invoke():List<TaskModel>{
        return taskManagerRepository.getAllNotes()
    }
}