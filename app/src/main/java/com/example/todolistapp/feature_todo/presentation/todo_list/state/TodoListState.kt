package com.example.todolistapp.feature_todo.presentation.todo_list.state

import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.use_case.GetTodoList
import com.example.todolistapp.feature_todo.domain.util.OrderType
import com.example.todolistapp.feature_todo.domain.util.TodoOrder

data class TodoListState(
    var todoList: List<TodoModel> = emptyList(),
    var todoOrder: TodoOrder = TodoOrder.Date(OrderType.DescendingOrder),
    var orderVisible: Boolean = false,
    var errorMessage: String = ""
)
