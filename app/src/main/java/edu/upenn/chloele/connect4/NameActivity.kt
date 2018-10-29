package edu.upenn.chloele.connect4

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        button.setOnClickListener(View.OnClickListener {
            val player1Name = editPlayer1.text.toString()
            val player2Name = editPlayer2.text.toString()
            if (player1Name.equals("") || player2Name.equals("")) {
                val toast = Toast.makeText(applicationContext,
                        "Please Enter Both Players' Names", Toast.LENGTH_SHORT)
                toast.show()
            } else if (player1Name.equals(player2Name)) {
                val toast = Toast.makeText(applicationContext,
                        "Please Make Sure Players Have Different Names", Toast.LENGTH_SHORT)
                toast.show()
            } else {
                val mainIntent = Intent(applicationContext, MainActivity::class.java)
                mainIntent.putExtra("Player1Name", player1Name)
                mainIntent.putExtra("Player2Name", player2Name)
                startActivity(mainIntent)
                finish()
            }
        })
    }

}