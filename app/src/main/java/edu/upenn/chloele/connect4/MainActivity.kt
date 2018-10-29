package edu.upenn.chloele.connect4

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.graphics.LightingColorFilter
import android.graphics.PorterDuff.Mode



class MainActivity : AppCompatActivity() {
    private var _COLUMN_STATUS = intArrayOf(0, 0, 0, 0, 0, 0, 0)
    private var _currPlayerColor = ""
    private var _currPlayer = 1
    private var _player1Name = ""
    private var _player2Name = ""
    private val _player1Color = "#ff4911"
    private val _player2Color = "#ffd711"
    private var _boardMatrix = Array(6, {IntArray(7)})
    private var _count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = getIntent()
        _player1Name = intent.getStringExtra("Player1Name")
        _player2Name = intent.getStringExtra("Player2Name")


        TextPlayer1Name.setText(_player1Name)
        TextPlayer2Name.setText(_player2Name)
        currPlayerName.setText(_player1Name)
        currPlayerName.setTextColor(Color.parseColor(_player1Color))
        _currPlayerColor = _player1Color


    }

    fun selectColumn(view: View) {
        val button : Button = view as Button
        val colNum = button.text.toString().toInt()
        val rowNum = 5 - _COLUMN_STATUS[colNum]
        if (rowNum < 0) {
            val toast = Toast.makeText(applicationContext,
                    "This column is full, please select another", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            val buttonID = "button" + rowNum.toString() + colNum.toString()
            var bID:Int = getResources().getIdentifier(buttonID, "id", getPackageName());
            val changeButton = findViewById(bID) as Button
            changeButton.getBackground().setColorFilter(
                    Color.parseColor(_currPlayerColor), Mode.MULTIPLY)
            // update
            _COLUMN_STATUS[colNum] = _COLUMN_STATUS[colNum] + 1
            _count++
            // toggle player
            if (_currPlayer == 1) {
                _boardMatrix[rowNum][colNum] = 1
                _currPlayer = 2
                _currPlayerColor = _player2Color
                currPlayerName.setText(_player2Name)

            } else {
                _boardMatrix[rowNum][colNum] = 2
                _currPlayer = 1
                _currPlayerColor = _player1Color
                currPlayerName.setText(_player1Name)
            }
            currPlayerName.setTextColor(Color.parseColor(_currPlayerColor))
            // check if won
            if (_count == 42) {
                //you tied
            }
        }
    }

    fun checkIfWon(playerNum : Int) {

    }
}
