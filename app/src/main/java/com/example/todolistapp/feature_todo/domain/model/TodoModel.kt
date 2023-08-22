package com.example.todolistapp.feature_todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity
data class TodoModel (
    @PrimaryKey
    val id : Int? = null,
    val title : String,
    val body : String,
    val dateTime : Long,
    val color : Int
){
    fun getDisplayTime():String{
        return try {
            SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.ENGLISH).format(Date(dateTime))
        }catch (e:Exception){
            ""
        }
    }
}