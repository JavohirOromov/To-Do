package com.javohir.to_do.presentation.screens.addtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javohir.to_do.domain.model.Todo
import com.javohir.to_do.domain.usecase.AddTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val addTodoUseCase: AddTodoUseCase
): ViewModel() {

    fun addTodo(title: String){
        viewModelScope.launch {
            if (title.isNotBlank()){
                val todo = Todo(title = title)
                addTodoUseCase(todo = todo)
            }
        }
    }
}