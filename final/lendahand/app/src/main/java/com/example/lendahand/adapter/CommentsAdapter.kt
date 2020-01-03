package com.example.lendahand.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lendahand.R

class CommentsAdapter(private val comments: List<String>) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.rv_text,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(comments[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSimpleText: TextView = itemView.findViewById(R.id.tvSimpleText)
        fun bind(comment: String) {
            tvSimpleText.text = comment
        }
    }
}