package com.example.advancedrecyclerviewkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.advancedrecyclerviewkotlin.R
import com.example.advancedrecyclerviewkotlin.model.ColorItem

class ColorAdapter(private val colors: List<ColorItem>) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_color, parent, false)
        )
    }

    override fun getItemCount(): Int = colors.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(colors[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(colorItem: ColorItem) {
            Glide.with(context).load(colorItem.getImageUrl()).into(itemView.ivColor)
        }
    }

}
