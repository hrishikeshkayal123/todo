package com.example.todolistapp.feature_todo.presentation.todo_list

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistapp.feature_todo.presentation.viewmodel.TodoViewModel

@Composable
fun TodoListScreen(
    navigation: NavController,
    viewModel : TodoViewModel = hiltViewModel()
) {


}