package com.example.todolist.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.data.Task
import com.example.todolist.databinding.ItemTaskBinding

class TasksAdapter(private val listener: OnItemClickListener):ListAdapter<Task, TasksAdapter.TaskViewHolder>(DiffCallback()) {

    inner class TaskViewHolder(private val binding:ItemTaskBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if(position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onItemClick(task)
                    }
                }
                checkBoxCompleted.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onCheckBoxClicked(task, checkBoxCompleted.isChecked)
                    }
                }
            }
        }
        fun bind(task:Task){
            binding.apply {
                checkBoxCompleted.isChecked=task.completed
                textViewName.text=task.name
                textViewName.paint.isStrikeThruText=task.completed
                labelPriority.isVisible=task.important
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding=ItemTaskBinding.inflate(LayoutInflater.from(parent.context))
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem=getItem(position)
        holder.bind(currentItem)
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)

        fun onCheckBoxClicked(task: Task, isChecked: Boolean)
    }

    class DiffCallback: DiffUtil.ItemCallback<Task>(){
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem.id==newItem.id


        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean =
            oldItem==newItem

    }
}