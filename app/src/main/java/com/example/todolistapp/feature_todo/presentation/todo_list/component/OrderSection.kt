package com.example.todolistapp.feature_todo.presentation.todo_list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.todolistapp.R
import com.example.todolistapp.feature_todo.domain.util.OrderType
import com.example.todolistapp.feature_todo.domain.util.TodoOrder

@Composable
fun OrderSection(
    todoOrder:TodoOrder = TodoOrder.Date(OrderType.DescendingOrder),
    onOrderSection:(TodoOrder)->Unit,
    modifier:Modifier
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = stringResource(R.string.date), isChecked = todoOrder is TodoOrder.Date, onCheck = {
                onOrderSection(TodoOrder.Date(todoOrder.orderType))
            })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = stringResource(R.string.title), isChecked = todoOrder is TodoOrder.Title, onCheck = {
                onOrderSection(TodoOrder.Title(todoOrder.orderType))
            })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = stringResource(R.string.color), isChecked = todoOrder is TodoOrder.Color, onCheck = {
                onOrderSection(TodoOrder.Color(todoOrder.orderType))
            })
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DefaultRadioButton(text = stringResource(R.string.ascending), isChecked = todoOrder.orderType is OrderType.AscendingOrder, onCheck = {
                onOrderSection(todoOrder.clone(OrderType.AscendingOrder))
            })
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(text = stringResource(R.string.descending), isChecked = todoOrder.orderType is OrderType.DescendingOrder, onCheck = {
                onOrderSection(todoOrder.clone(OrderType.DescendingOrder))
            })
        }
    }
}