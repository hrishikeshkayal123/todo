package com.example.todolistapp.feature_todo.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapp.feature_todo.data.data_source.local.TodoDao
import com.example.todolistapp.feature_todo.domain.model.TodoModel

@Database(entities = [TodoModel::class], version = 1, exportSchema = false)
abstract class TodoDataBase:RoomDatabase() {
    abstract val todoDao : TodoDao
    companion object{
        val DATABASE_NAME = "todo_db.db"
    }
}