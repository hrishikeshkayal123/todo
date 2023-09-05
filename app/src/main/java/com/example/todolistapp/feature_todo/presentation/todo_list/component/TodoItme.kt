package com.example.todolistapp.feature_todo.presentation.todo_list.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.example.todolistapp.feature_todo.domain.model.TodoModel

@Composable
fun TodoItem(
    modifier:Modifier = Modifier,
    item: TodoModel,
    cornerRadius: Dp = 6.dp,
    bendCornerWidth: Dp = 30.dp,
    onnDeleteClick:() -> Unit
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()){
            val clipPath = Path().apply {
                lineTo(size.width-bendCornerWidth.toPx(),0f)
                lineTo(size.width,bendCornerWidth.toPx())
                lineTo(size.width,size.height)
                lineTo(0f,size.height)
                close()
            }

            clipPath(clipPath){
                drawRoundRect(
                    color = Color(item.color),
                    size = size,
                    cornerRadius = CornerRadius( cornerRadius.toPx())
                )

                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(item.color,Color.Black.hashCode(),0.2f)
                    ),
                    topLeft= Offset(size.width-bendCornerWidth.toPx(),-10f),
                    size = Size(bendCornerWidth.toPx()+10f,bendCornerWidth.toPx()+10f),
                    cornerRadius = CornerRadius( cornerRadius.toPx())
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp, end = 16.dp, bottom = 8.dp, start = 8.dp)
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.body,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                maxLines = 6,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = onnDeleteClick) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "delete", tint = Color.Black)
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}