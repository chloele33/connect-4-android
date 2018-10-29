package edu.upenn.chloele.connect4

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = getIntent()
        val player1Name = intent.getStringExtra("Player1Name")
        val player2Name = intent.getStringExtra("Player2Name")
        val player1Color = "#ff4911"
        val player2Color = "#ffd711"
        val boardMatrix = Array(6, {IntArray(7)})

        TextPlayer1Name.setText(player1Name)
        TextPlayer2Name.setText(player2Name)
        currPlayerName.setText(player1Name)
        currPlayerName.setTextColor(Color.parseColor(player1Color))
        val currPlayerColor = player1Color


    }
}
