package com.example.todolistapp.feature_todo.presentation.todo_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistapp.feature_todo.presentation.todo_list.component.OrderSection
import com.example.todolistapp.feature_todo.presentation.todo_list.component.TodoItem
import com.example.todolistapp.feature_todo.presentation.todo_list.event.TodoListEvents
import com.example.todolistapp.feature_todo.presentation.todo_list.viewmodel.TodoViewModel

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
        snackbarHost = {
            SnackbarHost(snackBarState)
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .clip(CircleShape),
                onClick = {

                },
                contentColor = MaterialTheme.colorScheme.background
            ) {
                Icon(imageVector= Icons.Default.Add,contentDescription = "add todo", tint = MaterialTheme.colorScheme.primary)
            }
        }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Todo List", style = MaterialTheme.typography.headlineLarge)
                IconButton(onClick = {
                    viewModel.onEvent(TodoListEvents.OrderSectionVisible)
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                }
            }
            AnimatedVisibility(
                visible = state.orderVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    todoOrder = state.todoOrder,
                    onOrderSection = { order->
                        viewModel.onEvent(TodoListEvents.Order(order))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(items = state.todoList) {todoItem->
                    TodoItem(
                        item = todoItem,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                //todo: addEdit screen redirection need to add
                            },
                        onnDeleteClick = {
                            viewModel.onEvent(TodoListEvents.DeleteTodo(todoItem))
                            //todo: need to add snackbar
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
