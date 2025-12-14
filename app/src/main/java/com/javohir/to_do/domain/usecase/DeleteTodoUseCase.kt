package com.javohir.to_do.domain.usecase

import com.javohir.to_do.domain.model.Todo
import com.javohir.to_do.domain.repository.TodoRepository
import javax.inject.Inject

class DeleteTodoUseCase @Inject constructor(
    private val repository: TodoRepository
) {

    suspend operator fun invoke(todo: Todo){
        repository.deleteTodo(todo = todo)
    }
}