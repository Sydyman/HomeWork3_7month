package com.projectx.homework3_7month.data.repositoryImpl

import com.projectx.homework3_7month.data.database.dao.TaskManagerDao
import com.projectx.homework3_7month.data.dto.toData
import com.projectx.homework3_7month.data.dto.toDomain
import com.projectx.homework3_7month.domain.model.TaskModel
import com.projectx.homework3_7month.domain.repository.TaskManagerRepository

class TaskManagerRepositoryImpl(private val taskManagerDao: TaskManagerDao) :
    TaskManagerRepository {

    override suspend fun insertTask(taskModel: TaskModel) {
        taskManagerDao.insertTask(taskModel.toData())
    }

    override suspend fun getAllNotes(): List<TaskModel> {
        return taskManagerDao.getAllNotes().map { it.toDomain() }
    }

    override suspend fun getTaskByName(taskName: String): TaskModel? {
        return taskManagerDao.getTaskByName(taskName)?.toDomain()
    }

    override suspend fun updateTask(taskModel: TaskModel) {
        taskManagerDao.updateTask(taskModel.toData())

    }

    override suspend fun deleteTask(task: TaskModel) {
        taskManagerDao.deleteTask(task.toData())
    }
}