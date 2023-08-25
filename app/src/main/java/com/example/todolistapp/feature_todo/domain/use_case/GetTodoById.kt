package com.example.todolistapp.feature_todo.domain.use_case

import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.repository.TodoRepository

class GetTodoById (private val repository: TodoRepository) {
    suspend operator fun invoke(id: Int):TodoModel?{
        return repository.getTodoById(id)
    }
}