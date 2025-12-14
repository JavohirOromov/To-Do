package com.javohir.to_do.di
import android.content.Context
import androidx.room.Room
import com.javohir.to_do.data.local.room.TodoDao
import com.javohir.to_do.data.local.room.TodoDataBase
import com.javohir.to_do.data.repositoryImpl.TodoRepositoryImpl
import com.javohir.to_do.domain.repository.TodoRepository
import com.javohir.to_do.domain.usecase.AddTodoUseCase
import com.javohir.to_do.domain.usecase.DeleteTodoUseCase
import com.javohir.to_do.domain.usecase.GetAllTodosUseCase
import com.javohir.to_do.domain.usecase.UpdateTodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDataBase {
        return Room.databaseBuilder(
            context,
            TodoDataBase::class.java,
            "todo_database.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(database: TodoDataBase): TodoDao {
        return database.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(dao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(dao)
    }

    @Provides
    fun provideGetAllTodosUseCase(repository: TodoRepository): GetAllTodosUseCase {
        return GetAllTodosUseCase(repository)
    }

    @Provides
    fun provideAddTodoUseCase(repository: TodoRepository): AddTodoUseCase{
        return AddTodoUseCase(repository)
    }

    @Provides
    fun provideUpdateTodoUseCase(repository: TodoRepository): UpdateTodoUseCase{
        return UpdateTodoUseCase(repository)
    }

    @Provides
    fun provideDeleteTodoUseCase(repository: TodoRepository): DeleteTodoUseCase{
        return DeleteTodoUseCase(repository)
    }
}