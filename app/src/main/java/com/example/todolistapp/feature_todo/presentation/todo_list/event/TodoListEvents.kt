package com.example.todolistapp.feature_todo.presentation.todo_list.event

import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.util.TodoOrder

sealed class TodoListEvents{
    data class Order(val todoOrder:TodoOrder):TodoListEvents()
    data class DeleteTodo(val todo:TodoModel):TodoListEvents()
    object OrderSectionVisible:TodoListEvents()
    object UndoDeletingTodo:TodoListEvents()
}
