package com.mtjin.todoapp.data.repository

import androidx.lifecycle.LiveData
import com.mtjin.todoapp.data.ToDoDao
import com.mtjin.todoapp.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }
}