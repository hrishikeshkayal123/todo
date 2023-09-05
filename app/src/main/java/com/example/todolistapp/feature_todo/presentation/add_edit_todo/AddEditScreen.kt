package com.example.todolistapp.feature_todo.presentation.add_edit_todo

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistapp.feature_todo.presentation.add_edit_todo.component.TextEditField
import com.example.todolistapp.feature_todo.presentation.add_edit_todo.event.AddEditTodoEvent
import com.example.todolistapp.feature_todo.presentation.add_edit_todo.viewmodel.AddEditTodoViewModel
import com.example.todolistapp.feature_todo.presentation.common.components.SnackbarVisualsWithError
import com.example.todolistapp.feature_todo.presentation.common.components.snackBarCommon
import com.example.todolistapp.feature_todo.presentation.common.event.UiEvent
import com.example.todolistapp.feature_todo.presentation.util.TodoColors
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    navigation: NavController,
    todoColor: Int,
    viewModel : AddEditTodoViewModel = hiltViewModel()
) {
    val contentState = viewModel.bodyState
    val titleState = viewModel.titleState
    val snackBarState = remember { SnackbarHostState() }

    val bgColorAnimator = remember {
        Animatable(
            Color(if (todoColor != -1) todoColor else viewModel.colorState.value)
        )
    }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true ){
        viewModel.uiEventState.collectLatest {event->
            when(event){
                is UiEvent.SaveTodo->{
                    scope.launch {
                        snackBarState.showSnackbar(
                            SnackbarVisualsWithError(
                                "Saved successfully!!",
                                isError = false
                            )
                        )
                        navigation.navigateUp()
                    }

                }
                is UiEvent.ShowScankMessage->{
                    scope.launch {
                        snackBarState.showSnackbar(
                            SnackbarVisualsWithError(
                                "${event.message}",
                                isError = true,
                                errorLabel = "Dismiss"
                            )
                        )
                    }

                }
                else->{
                    //do nothing
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            snackBarCommon(snackBarState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(AddEditTodoEvent.SaveTodo)
                },
                contentColor=MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Done, contentDescription ="save",  )
            }
        }

    ) {it->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColorAnimator.value)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TodoColors.colors.forEach { clr->
                    val color = clr.toArgb()
                    Box(
                        modifier = Modifier
                            .size(45.dp)
                            .clip(CircleShape)
                            .shadow(
                                elevation = 15.dp,
                                shape = CircleShape
                            )
                            .background(color = clr)
                            .clickable {
                                scope.launch {
                                    bgColorAnimator.animateTo(
                                        targetValue = clr,
                                        animationSpec = tween(
                                            durationMillis = 400
                                        )
                                    )
                                    viewModel.onEvent(AddEditTodoEvent.ChangeColor(color))
                                }
                            }
                            .border(
                                width = 2.dp,
                                color = if (viewModel.colorState.value == color) Color.Black else Color.Transparent,
                                shape = CircleShape
                            )
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            TextEditField(
                text = titleState.value.todoText,
                hint = titleState.value.todoTextHint,
                onTextChanged = {txt->
                    viewModel.onEvent(AddEditTodoEvent.EnteredTitle(txt))
                },
                onFocusChanged = {fState->
                    viewModel.onEvent(AddEditTodoEvent.TitleFocusChanged(fState))
                },
                isHintVisible = titleState.value.isHintVisible,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                textStyle = MaterialTheme.typography.headlineMedium,

            )
            Spacer(modifier = Modifier.height(8.dp))
            TextEditField(
                text = contentState.value.todoText,
                hint = contentState.value.todoTextHint,
                onTextChanged = {txt->
                    viewModel.onEvent(AddEditTodoEvent.EnteredBody(txt))
                },
                onFocusChanged = {fState->
                    viewModel.onEvent(AddEditTodoEvent.BodyFocusChanged(fState))
                },
                isHintVisible = contentState.value.isHintVisible,
                singleLine = false,
                modifier = Modifier.fillMaxHeight(),
                textStyle = MaterialTheme.typography.headlineSmall,
            )
        }

    }

}
