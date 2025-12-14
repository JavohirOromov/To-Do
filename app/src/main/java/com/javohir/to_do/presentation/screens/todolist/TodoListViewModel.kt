package com.javohir.to_do.presentation.screens.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.javohir.to_do.domain.model.Todo
import com.javohir.to_do.domain.usecase.DeleteTodoUseCase
import com.javohir.to_do.domain.usecase.GetAllTodosUseCase
import com.javohir.to_do.domain.usecase.UpdateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val getAllTodosUseCase: GetAllTodosUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
): ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        loadTodos()
    }

    private fun loadTodos(){
        viewModelScope.launch {
            _isLoading.value = true
            getAllTodosUseCase().collectLatest { todoList ->
                _todos.value = todoList
                _isLoading.value = false
            }
        }
    }

    fun toggleTodoCompletion(todo: Todo){
        viewModelScope.launch {
            val updateTodo = todo.copy(isCompleted = !todo.isCompleted)
            updateTodoUseCase(updateTodo)
        }
    }
    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            deleteTodoUseCase(todo = todo)
        }
    }
}