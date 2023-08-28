package com.example.todolistapp.feature_todo.domain.use_case

import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.repository.TodoRepository
import com.example.todolistapp.feature_todo.domain.util.OrderType
import com.example.todolistapp.feature_todo.domain.util.TodoOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTodoList(private val repository: TodoRepository) {
    operator fun invoke(order:TodoOrder = TodoOrder.Date(OrderType.DescendingOrder)): Flow<List<TodoModel>> {
        return repository.getTodoList().map{ todos ->
            when(order.orderType){
                is OrderType.AscendingOrder->{
                    when(order){
                        is TodoOrder.Date->{
                            todos.sortedBy { it.dateTime }
                        }
                        is TodoOrder.Title->{
                            todos.sortedBy { it.title.lowercase() }
                        }
                        is TodoOrder.Color->{
                            todos.sortedBy { it.color }
                        }
                    }
                }
                is OrderType.DescendingOrder->{
                    when(order){
                        is TodoOrder.Date->{
                            todos.sortedByDescending { it.dateTime }
                        }
                        is TodoOrder.Title->{
                            todos.sortedByDescending { it.title.lowercase() }
                        }
                        is TodoOrder.Color->{
                            todos.sortedByDescending { it.color }
                        }
                    }
                }
            }
        }
    }
}