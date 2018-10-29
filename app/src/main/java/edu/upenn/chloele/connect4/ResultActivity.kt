package edu.upenn.chloele.connect4

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        var winnerName = intent.getStringExtra("WinnerName")
        var winnerColor = intent.getStringExtra("WinnerColor")
        Winner.setText(winnerName + " WON!!!")
        Winner.setTextColor(Color.parseColor(winnerColor))

        /** Hander to delay mainActivity for splash display length amount of seconds */
        Handler().postDelayed(Runnable {
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(applicationContext, MenuActivity::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)

    }
}