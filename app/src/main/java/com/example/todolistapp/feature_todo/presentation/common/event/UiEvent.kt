package com.example.todolistapp.feature_todo.presentation.common.event

sealed class UiEvent {
    data class ShowScankMessage(val message:String): UiEvent()
    object SaveTodo: UiEvent()
    object TodoDeleted: UiEvent()
}
