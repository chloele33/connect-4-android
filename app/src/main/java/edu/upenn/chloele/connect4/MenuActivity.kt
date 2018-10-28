package edu.upenn.chloele.connect4

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_menu.*


class MenuActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        buttonStartGame.setOnClickListener(View.OnClickListener {
            val mainIntent = Intent(applicationContext, NameActivity::class.java)
            startActivity(mainIntent)
            finish()
        })
    }
}