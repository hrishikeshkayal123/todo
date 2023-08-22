package com.example.todolistapp.common.database

import androidx.room.Database
import com.example.todolistapp.feature_todo.data.data_source.local.TodoDao
import com.example.todolistapp.feature_todo.domain.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDataBase {
    abstract val todoDao : TodoDao
}