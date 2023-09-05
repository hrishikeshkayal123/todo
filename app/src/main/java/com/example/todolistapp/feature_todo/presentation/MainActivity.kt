package com.example.todolistapp.feature_todo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistapp.feature_todo.presentation.add_edit_todo.AddEditScreen
import com.example.todolistapp.feature_todo.presentation.todo_list.TodoListScreen
import com.example.todolistapp.feature_todo.presentation.util.Screen
import com.example.todolistapp.feature_todo.presentation.util.TodoColors
import com.example.todolistapp.ui.theme.TodoListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoListAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.TodoListScreen.route){
                        composable(route = Screen.TodoListScreen.route){
                            TodoListScreen(navigation = navController)
                        }
                        composable(route = Screen.AddEditScreen.route + "?todoId={todoId}&todoColor={todoColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "todoId"
                                ){
                                    type= NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "todoColor"
                                ){
                                    type= NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            val color = it.arguments?.getInt("todoColor")
                            AddEditScreen(navigation = navController, todoColor = color?:-1)
                        }
                    }
                }
            }
        }
    }
}
