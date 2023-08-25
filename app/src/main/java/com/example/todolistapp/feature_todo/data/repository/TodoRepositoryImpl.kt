package com.example.todolistapp.feature_todo.data.repository

import com.example.todolistapp.feature_todo.data.data_source.local.TodoDao
import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(val todoDao: TodoDao): TodoRepository {
    override fun getTodoList(): Flow<List<TodoModel>> {
        return todoDao.getTodoList()
    }

    override suspend fun getTodoById(id: Int): TodoModel? {
       return todoDao.getTodo(id)
    }

    override suspend fun saveTodo(todoModel: TodoModel) {
        todoDao.saveTodo(todoModel)
    }

    override suspend fun deleteTodo(todoModel: TodoModel) {
        todoDao.deleteTodo(todoModel)
    }
}