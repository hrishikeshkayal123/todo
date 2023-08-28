package com.example.todolistapp.feature_todo.domain.util

sealed class OrderType {
    object AscendingOrder: OrderType()
    object DescendingOrder: OrderType()
}