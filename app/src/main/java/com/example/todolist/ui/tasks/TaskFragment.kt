package com.example.todolist.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.todolist.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskFragment:Fragment(R.layout.fragment_tasks) {

    private val viewModel:TaskViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}