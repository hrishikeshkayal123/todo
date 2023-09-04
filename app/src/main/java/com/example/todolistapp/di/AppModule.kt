package com.example.todolistapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.todolistapp.feature_todo.data.data_source.local.TodoDataBase
import com.example.todolistapp.feature_todo.data.repository.TodoRepositoryImpl
import com.example.todolistapp.feature_todo.domain.repository.TodoRepository
import com.example.todolistapp.feature_todo.domain.use_case.DeleteTodo
import com.example.todolistapp.feature_todo.domain.use_case.GetTodoById
import com.example.todolistapp.feature_todo.domain.use_case.GetTodoList
import com.example.todolistapp.feature_todo.domain.use_case.InsertTodo
import com.example.todolistapp.feature_todo.domain.use_case.TodoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context):TodoDataBase{
        return Room.databaseBuilder(
            context = context.applicationContext,
            klass = TodoDataBase::class.java,
            name = TodoDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(db:TodoDataBase):TodoRepository{
        return TodoRepositoryImpl(db.todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoUseCase(repository:TodoRepository):TodoUseCase{
        return TodoUseCase(
            getTodoList = GetTodoList(repository),
            getTodoById = GetTodoById(repository),
            deleteTodo = DeleteTodo(repository),
            insertTodo = InsertTodo(repository)
        )
    }

}