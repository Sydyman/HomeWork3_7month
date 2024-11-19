package com.projectx.addtask

import androidx.lifecycle.ViewModel
import com.projectx.homework3_7month.domain.usecase.InsertTaskUseCase
import com.projectx.homework3_7month.presentation.model.TaskUI
import com.projectx.homework3_7month.presentation.model.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope

class AddTaskViewModel(
    private val insertTaskUseCase: InsertTaskUseCase
):ViewModel() {

    private val _insertMessageStateFlow = MutableStateFlow(String())
    val insertMessageFlow: StateFlow<String> = _insertMessageStateFlow.asStateFlow()


    fun insertTask(taskUI: TaskUI) {
        viewModelScope.launch(Dispatchers.IO) {
            val message = insertTaskUseCase.insertTask(taskModel = taskUI.toDomain())
            _insertMessageStateFlow.value = message
        }
    }

}