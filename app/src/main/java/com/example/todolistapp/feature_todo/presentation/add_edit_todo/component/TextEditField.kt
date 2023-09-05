package com.example.todolistapp.feature_todo.presentation.add_edit_todo.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TextEditField(
    modifier: Modifier = Modifier,
    text: String,
    hint: String,
    onTextChanged:(String)->Unit,
    singleLine:Boolean = false,
    isHintVisible:Boolean = true,
    textStyle: TextStyle = TextStyle(),
    onFocusChanged: (FocusState)->Unit
) {
    Box(modifier = modifier){
        BasicTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier= Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChanged(it) },
            textStyle = textStyle,
            singleLine = singleLine
        )

        if(isHintVisible){
            BasicText(
                text = hint,
                style = textStyle,
                color =  { Color.DarkGray }
            )
        }
    }
}