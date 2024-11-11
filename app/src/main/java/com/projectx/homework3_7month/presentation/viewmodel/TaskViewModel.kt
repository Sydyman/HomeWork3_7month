package com.projectx.homework3_7month.presentation.viewmodel

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectx.homework3_7month.data.dto.TaskDto
import com.projectx.homework3_7month.data.repositoryImpl.TaskManagerRepositoryImpl
import com.projectx.homework3_7month.domain.usecase.GetAllNotesUseCase
import com.projectx.homework3_7month.domain.usecase.InsertTaskUseCase
import com.projectx.homework3_7month.domain.usecase.TaskDelete
import com.projectx.homework3_7month.domain.usecase.UpdateTaskUseCase
import com.projectx.homework3_7month.presentation.model.TaskUI
import com.projectx.homework3_7month.presentation.model.toDomain
import com.projectx.homework3_7month.presentation.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(
    private val insertTaskUseCase: InsertTaskUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val delete: TaskDelete
):ViewModel() {
    private val _tasks = MutableLiveData<List<TaskUI>>()
    val tasks: LiveData<List<TaskUI>> get() = _tasks

    private val _insertMessage = MutableLiveData<String>()
    val insertMessage: LiveData<String> get() = _insertMessage

    private val _updateMessage = MutableLiveData<String>()
    val updateMessage: LiveData<String> get() = _updateMessage

    fun insertTask(taskUI: TaskUI) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = insertTaskUseCase.insertTask(taskUI.toDomain())
            _insertMessage.postValue(message)
        }
    }

    fun loadTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            val allTasks = getAllNotesUseCase()
            Log.d("TaskViewModel", "Loaded tasks: $allTasks")
            _tasks.postValue(allTasks.map { it.toUi() })
        }

    }

    fun updateTask(taskUI: TaskUI) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = updateTaskUseCase.updateTask(taskUI.toDomain())
            _updateMessage.postValue(message)
        }
    }
    fun deleteTask(taskUI: TaskUI) {
        viewModelScope.launch {
            try {
                delete.deleteTask(taskUI.toDomain())
                loadTasks()
            } catch (e: Exception) {
                Log.e("sydyman", "Ошибка : ${e.message}")
            }
        }
    }
}