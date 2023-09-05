package com.example.todolistapp.feature_todo.presentation.common.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class SnackbarVisualsWithError(
    override val message: String,
    val isError: Boolean,
    val errorLabel:String?=null
) : SnackbarVisuals {
    override val actionLabel: String
        get() = if (isError) errorLabel?:"ERROR" else "OK"
    override val withDismissAction: Boolean
        get() = false
    override val duration: SnackbarDuration
        get() = SnackbarDuration.Indefinite
}

@Composable
fun snackBarCommon(snackBarState: SnackbarHostState) {
    SnackbarHost(snackBarState){ data ->
        val isError = (data.visuals as? SnackbarVisualsWithError)?.isError ?: false
        val buttonColor = if (isError) {
            ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
                contentColor = MaterialTheme.colorScheme.error
            )
        } else {
            ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colorScheme.inversePrimary
            )
        }

        Snackbar(
            modifier = Modifier
                .padding(12.dp),
            action = {
                TextButton(
                    onClick = { if (isError) data.dismiss() else data.performAction() },
                    colors = buttonColor
                ) { Text(data.visuals.actionLabel ?: "") }
            }
        ) {
            Text(data.visuals.message)
        }

    }
}