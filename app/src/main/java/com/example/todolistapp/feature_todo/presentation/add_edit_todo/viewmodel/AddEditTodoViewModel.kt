package com.example.todolistapp.feature_todo.presentation.add_edit_todo.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.use_case.TodoUseCase
import com.example.todolistapp.feature_todo.domain.util.InvalidTodoException
import com.example.todolistapp.feature_todo.presentation.add_edit_todo.event.AddEditTodoEvent
import com.example.todolistapp.feature_todo.presentation.add_edit_todo.state.TextEditState
import com.example.todolistapp.feature_todo.presentation.common.event.UiEvent
import com.example.todolistapp.feature_todo.presentation.util.TodoColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(private val useCase: TodoUseCase, private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val _titleState = mutableStateOf(TextEditState(
        todoTextHint = "Enter title"
    ))
    val titleState: MutableState<TextEditState> = _titleState

    private val _bodyState = mutableStateOf(TextEditState(
        todoTextHint = "Enter content"
    ))
    val bodyState: MutableState<TextEditState> = _bodyState

    private val _colorState = mutableIntStateOf(TodoColors.colors.random().toArgb())
    val colorState: MutableState<Int> = _colorState

    private val _uiEventState = MutableSharedFlow<UiEvent>()
    val uiEventState: SharedFlow<UiEvent> = _uiEventState.asSharedFlow()
    private var currentTodoId:Int? = null

    init {
        savedStateHandle.get<Int>("todoId")?.let{
            viewModelScope.launch {
                useCase.getTodoById(it)?.apply {
                    currentTodoId = this.id
                    _bodyState.value = bodyState.value.copy(
                        todoText = this.body,
                        isHintVisible = this.body.isEmpty()
                    )
                    _titleState.value = titleState.value.copy(
                        todoText = this.title,
                        isHintVisible = this.title.isEmpty()
                    )
                    _colorState.value = this.color

                }
            }
        }
    }


    fun onEvent(event: AddEditTodoEvent){
        when(event){
            is AddEditTodoEvent.EnteredBody->{
                _bodyState.value = bodyState.value.copy(
                    todoText = event.value
                )
            }
            is AddEditTodoEvent.BodyFocusChanged->{
                _bodyState.value = bodyState.value.copy(
                    isHintVisible = !event.state.isFocused && bodyState.value.todoText.isBlank()
                )
            }
            is AddEditTodoEvent.EnteredTitle->{
                _titleState.value = titleState.value.copy(
                    todoText = event.value
                )
            }
            is AddEditTodoEvent.TitleFocusChanged->{
                _titleState.value = titleState.value.copy(
                    isHintVisible = !event.state.isFocused && titleState.value.todoText.isBlank()
                )
            }
            is AddEditTodoEvent.ChangeColor->{
                _colorState.value = event.color
            }
            is AddEditTodoEvent.SaveTodo->{
                viewModelScope.launch {
                    try{
                        useCase.insertTodo(TodoModel(
                            title = titleState.value.todoText,
                            body = bodyState.value.todoText,
                            color = colorState.value,
                            dateTime = System.currentTimeMillis(),
                            id = currentTodoId
                        ))
                        _uiEventState.emit(UiEvent.SaveTodo)
                    }catch (ex:InvalidTodoException){
                        _uiEventState.emit(UiEvent.ShowScankMessage(ex.message?:"Unknown error."))
                    }
                }
            }
        }
    }


}