package com.example.todolistapp.feature_todo.domain.use_case

import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.repository.TodoRepository

class InsertTodo(private val repository: TodoRepository) {
    suspend operator fun invoke(todoModel: TodoModel){
        repository.saveTodo(todoModel)
    }
}