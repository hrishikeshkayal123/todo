package com.example.todolistapp.feature_todo.presentation.todo_list.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.feature_todo.domain.model.TodoModel
import com.example.todolistapp.feature_todo.domain.use_case.TodoUseCase
import com.example.todolistapp.feature_todo.domain.util.OrderType
import com.example.todolistapp.feature_todo.domain.util.TodoOrder
import com.example.todolistapp.feature_todo.presentation.common.event.UiEvent
import com.example.todolistapp.feature_todo.presentation.todo_list.event.TodoListEvents
import com.example.todolistapp.feature_todo.presentation.todo_list.state.TodoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val useCase: TodoUseCase): ViewModel() {
    private val _state = mutableStateOf(TodoListState())
    val state: State<TodoListState> = _state

    private val _uiEventState = MutableSharedFlow<UiEvent>()
    val uiEventState: SharedFlow<UiEvent> = _uiEventState.asSharedFlow()

    private var lastDeletedTodo: TodoModel? = null
    private var getTodoListJob: Job? = null

    init {
        getTodoList(TodoOrder.Date(OrderType.DescendingOrder))
    }

    fun onEvent(event:TodoListEvents){
        when(event){
            is TodoListEvents.UndoDeletingTodo->{
                lastDeletedTodo?.let {
                    viewModelScope.launch {
                        useCase.insertTodo(it)
                    }
                    lastDeletedTodo = null
                }
            }
            is TodoListEvents.DeleteTodo->{
                viewModelScope.launch {
                    lastDeletedTodo = event.todo
                    useCase.deleteTodo(event.todo)
                    _uiEventState.emit(UiEvent.TodoDeleted)
                }
            }
            is TodoListEvents.OrderSectionVisible->{
                _state.value = state.value.copy(
                    orderVisible = !state.value.orderVisible
                )
            }
            is TodoListEvents.Order->{
                if(state.value.todoOrder::class==event.todoOrder::class && state.value.todoOrder.orderType==event.todoOrder.orderType){
                    return
                }
                getTodoList(event.todoOrder)
            }
        }
    }

    private fun getTodoList(order: TodoOrder) {
        getTodoListJob?.cancel()
        getTodoListJob = useCase.getTodoList(order)
            .onEach {
                _state.value = state.value.copy(
                    todoList = it,
                    todoOrder = order
                )
            }
            .catch {
                 _uiEventState.emit(UiEvent.ShowScankMessage(it.message ?:""))
            }
            .launchIn(viewModelScope)
    }

}