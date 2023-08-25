package com.example.todolistapp.feature_todo.domain.repository

import com.example.todolistapp.feature_todo.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow

interface TodoRepository{
    fun getTodoList(): Flow<List<TodoModel>>
    suspend fun getTodoById(id: Int): TodoModel?
    suspend fun saveTodo(todoModel: TodoModel)
    suspend fun deleteTodo(todoModel: TodoModel)
}