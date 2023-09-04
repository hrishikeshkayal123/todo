package com.example.todolistapp.feature_todo.presentation.todo_list.event

sealed class UiEvent {
    data class ShowScankMessage(val message:String):UiEvent()
    object SaveTodo:UiEvent()
}
