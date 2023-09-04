package com.example.todolistapp.feature_todo.presentation.util

sealed class Screen(val route: String){
    object TodoListScreen: Screen("todo_list_screen")
    object AddEditScreen: Screen("add_edit_screen")
}
