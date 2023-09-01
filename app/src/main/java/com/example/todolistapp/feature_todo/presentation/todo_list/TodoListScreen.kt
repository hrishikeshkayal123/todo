package com.example.todolistapp.feature_todo.presentation.todo_list

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistapp.feature_todo.presentation.viewmodel.TodoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    navigation: NavController,
    viewModel : TodoViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                contentColor = MaterialTheme.colorScheme.background
            ) {
                Icon(imageVector= Icons.Default.Add,contentDescription = "add todo")
            }
        }
    ) {

    }
}
