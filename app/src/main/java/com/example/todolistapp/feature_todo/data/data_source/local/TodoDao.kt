package com.example.todolistapp.feature_todo.data.data_source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todolistapp.feature_todo.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Query("SELECT * FROM todomodel")
    fun getTodoList(): Flow<List<TodoModel>>

    @Query("SELECT * FROM todomodel WHERE id=:id")
    suspend fun getTodo(id: Int): TodoModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTodo(todo: TodoModel)

    @Delete
    fun deleteTodo(todo: TodoModel)

}