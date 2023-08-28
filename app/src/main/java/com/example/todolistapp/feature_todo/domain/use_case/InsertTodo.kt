package com.example.todolistapp.feature_todo.domain.use_case

import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.repository.TodoRepository
import com.example.todolistapp.feature_todo.domain.util.InvalidTodoException

class InsertTodo(private val repository: TodoRepository) {
    @Throws(InvalidTodoException::class)
    suspend operator fun invoke(todoModel: TodoModel){
        if(validateTodo(todoModel = todoModel)){
            repository.saveTodo(todoModel)
        }
    }
    @Throws(InvalidTodoException::class)
    private fun validateTodo(todoModel: TodoModel):Boolean{
        return when{
            todoModel.title.isBlank()->throw InvalidTodoException("Title is empty.")
            todoModel.body.isBlank()->throw InvalidTodoException("Content is empty.")
            else->true
        }
    }
}