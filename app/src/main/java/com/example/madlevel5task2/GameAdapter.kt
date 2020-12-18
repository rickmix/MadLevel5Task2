package com.example.madlevel5task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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

            val dateFormat = SimpleDateFormat("dd EEEE yyyy")
            try {
                val date = game.release
                val dateTime: String = dateFormat.format(date)
                itemView.dateTime.text = "Release: $dateTime"

//                val sdf = SimpleDateFormat("EEEE")
//                val d = Date()
//                val dayOfTheWeek: String = sdf.format(d)

            } catch (e: ParseException) {
                e.printStackTrace()
            }

        }
    }

}