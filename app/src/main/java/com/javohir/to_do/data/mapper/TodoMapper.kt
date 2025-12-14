package com.javohir.to_do.data.mapper
import com.javohir.to_do.data.model.TodoItem
import com.javohir.to_do.domain.model.Todo


    fun TodoItem.toDomain(): Todo{
        return Todo(
            id = this.id,
            title = this.title,
            isCompleted = this.isCompleted,
            createdAt = this.createdAt
        )
    }

    fun Todo.toData(): TodoItem{
        return TodoItem(
            id = this.id,
            title = this.title,
            isCompleted = this.isCompleted,
            createdAt = this.createdAt
        )
    }
