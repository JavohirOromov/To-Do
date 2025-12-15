package com.javohir.to_do.data.repositoryImpl
import com.javohir.to_do.data.local.room.TodoDao
import com.javohir.to_do.data.mapper.toData
import com.javohir.to_do.data.mapper.toDomain
import com.javohir.to_do.domain.model.Todo
import com.javohir.to_do.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
): TodoRepository {

    override fun getAllTodos(): Flow<List<Todo>> {
        return todoDao.getAllTodos().map { todoItems ->
            todoItems.map { it.toDomain() }
        }
    }

    override suspend fun insertTodo(todo: Todo) {
      withContext(Dispatchers.IO) {todoDao.insertTodo(todo.toData())}
    }

    override suspend fun updateTodo(todo: Todo) {
      withContext(Dispatchers.IO) {todoDao.updateTodo(todo.toData())}
    }

    override suspend fun deleteTodo(todo: Todo) {
      withContext(Dispatchers.IO){todoDao.deleteTodo(todo.toData())}
    }
}