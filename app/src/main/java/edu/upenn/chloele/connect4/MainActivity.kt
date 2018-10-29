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
                _boardMatrix[-1 * (rowNum - 5)][colNum] = 1
                if (checkIfWon(1)) {
                    val toast = Toast.makeText(applicationContext,
                            "PLAYER 1 WON", Toast.LENGTH_SHORT)
                    toast.show()
                }
                _currPlayer = 2
                _currPlayerColor = _player2Color
                currPlayerName.setText(_player2Name)

            } else {
                _boardMatrix[-1 * (rowNum - 5)][colNum] = 2
                if (checkIfWon(2)) {
                    val toast = Toast.makeText(applicationContext,
                            "PLAYER 2 WON", Toast.LENGTH_SHORT)
                    toast.show()
                }
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

    private fun checkIfWon(playerNum : Int) : Boolean {
        for (col in 0 until 7) {
            if (connect(playerNum, 0, 1, col, 0, 0)
                    || connect(playerNum, 1, 1, col, 0, 0)
                    || connect(playerNum, -1, 1, col, 0, 0)) {
                return true
            }
        }
        for (row in 0 until 6) {
            if (connect(playerNum, 1, 0, 0, row, 0)
                    || connect(playerNum, 1, 1, 0, row, 0)
                    || connect(playerNum, -1, 1, 7 - 1, row, 0)) {
                return true
            }
        }
        return false
    }

    private fun connect(playerNum : Int, dirX : Int, dirY : Int, col : Int, row : Int, totalConnected : Int) : Boolean {
        if (totalConnected >= 4) {
            return true
        }
        // check if out of bounds
        if (row < 0 || col < 0 || col >= 7 || row >= 6) {
            return false
        }
        if (_boardMatrix[row][col].toInt() == playerNum) {
            return connect(playerNum, dirX, dirY, col + dirX, row + dirY, totalConnected + 1);
        } else {
            return connect(playerNum, dirX, dirY, col + dirX, row + dirY, 0);
        }
    }
}
