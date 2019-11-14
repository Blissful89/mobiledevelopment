package com.example.gamebacklog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebacklog.R
import com.example.gamebacklog.model.Game
import java.text.SimpleDateFormat
import java.util.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_game,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(games[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvGameTitle: TextView = itemView.findViewById(R.id.tvGameTitle)
        private val tvGamePlatform: TextView = itemView.findViewById(R.id.tvGamePlatform)
        private val tvGameReleaseDate: TextView = itemView.findViewById(R.id.tvGameReleaseDate)

        fun bind(game: Game) {
            tvGameTitle.text = game.title
            tvGamePlatform.text = game.platform
            tvGameReleaseDate.text = itemView.context.applicationContext.getString(
                R.string.release_date,
                SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(game.releaseDate)
            )
        }
    }
}