package edu.upenn.chloele.connect4

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Adapter
import android.view.ViewGroup
import android.content.Context
import android.view.LayoutInflater;
import android.widget.TextView;

class LeaderboardAdapter (context:Context, private val scoreMap :Map<String, Int>) :
        RecyclerView.Adapter<LeaderboardAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardAdapter.ViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context).
                inflate(R.layout.player_score, parent, false) as View
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var player:String = scoreMap.keys.toTypedArray()[position] as String
        var score:Int = scoreMap.get(player) as Int
        var playerView = holder.getPlayerTextView()
        var scoreView = holder.getScoreTextView()
        playerView.setText(player)
        scoreView.setText(score.toString())
    }

    override fun getItemCount() = scoreMap.size

    class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        private var playerTextView = v.findViewById(R.id.player) as TextView
        private var scoreTextView = v.findViewById(R.id.score) as TextView

        fun getPlayerTextView() : TextView {
            return playerTextView
        }

        fun getScoreTextView() : TextView {
            return scoreTextView
        }
    }



}