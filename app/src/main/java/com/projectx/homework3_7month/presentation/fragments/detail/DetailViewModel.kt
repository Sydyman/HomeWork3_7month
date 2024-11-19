package com.projectx.homework3_7month.presentation.fragments.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectx.homework3_7month.domain.usecase.GetTaskUseCase
import com.projectx.homework3_7month.domain.usecase.UpdateTaskUseCase
import com.projectx.homework3_7month.presentation.model.TaskUI
import com.projectx.homework3_7month.presentation.model.toDomain
import com.projectx.homework3_7month.presentation.model.toUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val getTaskUseCase: GetTaskUseCase):ViewModel() {

    private val _updateMessageStateFlow = MutableStateFlow(String())
    val updateMessageFlow: StateFlow<String> = _updateMessageStateFlow

    suspend fun getTask(id: Int) = getTaskUseCase(id)?.toUi()

    fun updateTask(taskUI: TaskUI) {
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase.updateTask(taskUI.toDomain())
        }
    }


}