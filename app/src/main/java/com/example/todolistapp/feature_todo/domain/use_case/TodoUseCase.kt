package com.example.todolistapp.feature_todo.domain.use_case

data class TodoUseCase(
    val getTodoList:GetTodoList,
    val deleteTodo: DeleteTodo,
    val insertTodo: InsertTodo,
    val getTodoById: GetTodoById,
)