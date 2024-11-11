package com.projectx.homework3_7month.presentation.model

import com.projectx.homework3_7month.domain.model.TaskModel

data class TaskUI(
    val id: Int,
    val taskName: String,
    val taskDate: String,
    val taskPhoto: String
)

fun TaskUI.toDomain() = TaskModel(id, taskName, taskDate,taskPhoto)
fun TaskModel.toUi() = TaskUI(id, taskName, taskDate,taskPhoto)
