package com.example.madlevel5task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_second.view.*
import kotlinx.android.synthetic.main.game.view.*

class GameAdapter(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.game, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBind(games[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // val binding = ItemReminderBinding.bind(itemView)

        fun dataBind(game: Game) {
            itemView.titleText.text = game.title
            itemView.platformText.text = game.platform
            itemView.dateTime.text = game.release.toString()
        }
    }

}