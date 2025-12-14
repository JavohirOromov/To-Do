package com.javohir.to_do.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.javohir.to_do.data.model.TodoItem

@Database(
    entities = [TodoItem::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDataBase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}