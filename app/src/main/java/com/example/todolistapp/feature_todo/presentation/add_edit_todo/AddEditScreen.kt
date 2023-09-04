package com.example.todolistapp.feature_todo.presentation.add_edit_todo

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistapp.feature_todo.presentation.add_edit_todo.viewmodel.AddEditTodoViewModel

@Composable
fun AddEditScreen(
    navigation: NavController,
    viewModel : AddEditTodoViewModel = hiltViewModel()
) {
    val contentState = viewModel.bodyState
    val titleState = viewModel.titleState
    val colorState = viewModel.colorState



}