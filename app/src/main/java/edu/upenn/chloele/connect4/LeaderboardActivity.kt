package edu.upenn.chloele.connect4

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_result.*
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.LinearLayoutManager





class LeaderboardActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderboard)

        var rankingboard = findViewById(R.id.rankingboard) as RecyclerView
        val SP = this.getSharedPreferences("SCORES", Context.MODE_PRIVATE)
        val allScores = SP.all as Map<String, Int>
        var adapt = LeaderboardAdapter(this, allScores)
        var viewManager = LinearLayoutManager(this)
        rankingboard.apply {
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter
            adapter = adapt
        }

    }
}