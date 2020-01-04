package com.example.lendahand.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lendahand.R
import com.example.lendahand.model.Task

class TaskAdapter(private val tasks: List<Task>, private val clickListener: (Task) -> Unit) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.overview_task,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTaskOverviewTitle: TextView = itemView.findViewById(R.id.tvTaskOverviewTitle)
        private val tvTaskOverviewDate: TextView = itemView.findViewById(R.id.tvTaskOverviewDate)

        fun bind(task: Task) {
            tvTaskOverviewTitle.text = task.title
            tvTaskOverviewDate.text = task.date
            itemView.setOnClickListener { clickListener(task) }
        }
    }
}