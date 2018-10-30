package edu.upenn.chloele.connect4

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_leaderboard.*
import android.content.Intent
import android.view.Menu
import android.view.MenuItem


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

        clear.setOnClickListener(View.OnClickListener {
            // create a dialog box that requests confirmation whether the user wants to clear
            // the leaderboard, which should only be cleared if the user confirms.
            val builder = AlertDialog.Builder(this@LeaderboardActivity)
            builder.setTitle("Clear Leaderboard History")
            builder.setMessage("Are you sure you would like to reset the leaderboard?")

            builder.setPositiveButton("YES"){dialog, which ->
                // Do something when user press the positive button
                Toast.makeText(applicationContext,"Leaderboard Cleared",Toast.LENGTH_SHORT).show()
                val SP = getSharedPreferences("SCORES", Context.MODE_PRIVATE)
                val edit = SP.edit()
                edit.clear().commit()
                startActivity(Intent(baseContext, MenuActivity::class.java))
                finish()
            }

            builder.setNegativeButton("NO"){dialog,which ->
                // Do nothing
            }

            val dialog: AlertDialog = builder.create()

            dialog.show()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.leaderboard -> {
                val mainIntent = Intent(applicationContext, LeaderboardActivity::class.java)
                startActivity(mainIntent)
                true
            }
            R.id.menu -> {
                val mainIntent = Intent(applicationContext, MenuActivity::class.java)
                startActivity(mainIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}