package com.projectx.homework3_7month.presentation.fragments.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projectx.homework3_7month.domain.usecase.GetAllNotesUseCase
import com.projectx.homework3_7month.domain.usecase.InsertTaskUseCase
import com.projectx.homework3_7month.domain.usecase.TaskDelete
import com.projectx.homework3_7month.domain.usecase.UpdateTaskUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    protected fun runLaunchIO(block: suspend () -> Unit) {
        _loadingState.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _loadingState.value = false
            }
        }
    }
}

