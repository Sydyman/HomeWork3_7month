package com.projectx.homework3_7month.domain.usecase

import com.projectx.homework3_7month.domain.model.TaskModel
import com.projectx.homework3_7month.domain.repository.TaskManagerRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(
    private val taskManagerRepository: TaskManagerRepository
) {

    suspend operator fun invoke(): Flow<List<TaskModel>>{
        return taskManagerRepository.getAllNotes()
    }
}