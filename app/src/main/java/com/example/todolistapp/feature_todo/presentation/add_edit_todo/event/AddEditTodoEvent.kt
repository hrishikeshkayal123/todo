package com.example.todolistapp.feature_todo.presentation.add_edit_todo.event

import androidx.compose.ui.focus.FocusState

sealed class AddEditTodoEvent{
    data class EnteredTitle(val value:String): AddEditTodoEvent()
    data class TitleFocusChanged(val state:FocusState): AddEditTodoEvent()
    data class EnteredBody(val value:String): AddEditTodoEvent()
    data class BodyFocusChanged(val state:FocusState): AddEditTodoEvent()
    data class ChangeColor(val color:Int): AddEditTodoEvent()
    object SaveTodo:AddEditTodoEvent()
}
