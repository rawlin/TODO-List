package com.example.todolist.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.todolist.data.TaskDao

class TaskViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
):ViewModel() {

    val tasks = taskDao.getAllTask().asLiveData()


}