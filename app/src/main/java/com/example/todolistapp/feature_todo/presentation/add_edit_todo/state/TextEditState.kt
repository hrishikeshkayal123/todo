package com.example.todolistapp.feature_todo.presentation.add_edit_todo.state

data class TextEditState(
    val todoText:String = "",
    val todoTextHint: String = "",
    val isHintVisible:Boolean = true
)
