package com.christianrodier.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    var player1Wins = 0
    var player2Wins = 0

    lateinit var tvPlayer1Score: TextView
    lateinit var tvPlayer2Score: TextView

    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn6: Button
    lateinit var btn7: Button
    lateinit var btn8: Button
    lateinit var btn9: Button

    var allButtons = ArrayList<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvPlayer1Score = findViewById(R.id.tvPlayer1Wins)
        tvPlayer2Score = findViewById(R.id.tvPlayer2Wins)

        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)

        allButtons.add(btn1)
        allButtons.add(btn2)
        allButtons.add(btn3)
        allButtons.add(btn4)
        allButtons.add(btn5)
        allButtons.add(btn6)
        allButtons.add(btn7)
        allButtons.add(btn8)
        allButtons.add(btn9)

    }



    fun btnClick(view: View) {
        val btnSelected = view as Button

        var cellID = 0


        when(btnSelected.id){
            R.id.btn1 -> cellID = 1
            R.id.btn2 -> cellID = 2
            R.id.btn3 -> cellID = 3
            R.id.btn4 -> cellID = 4
            R.id.btn5 -> cellID = 5
            R.id.btn6 -> cellID = 6
            R.id.btn7 -> cellID = 7
            R.id.btn8 -> cellID = 8
            R.id.btn9 -> cellID = 9
        }

       // Log.d("btnClick", btnSelected.id.toString())
        //Log.d("btnClick", cellID.toString())

        playGame(cellID, btnSelected)

    }

    var activePlayer = 1

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()

    private fun playGame(CellId:Int, btnSelected: Button){

        if (activePlayer == 1){
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.gold)
            player1.add(CellId)
            activePlayer = 2

            autoPlay()

        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundResource(R.color.goldenRod)
            player2.add(CellId)
            activePlayer = 1
        }

        btnSelected.isEnabled = false

        checkWinner()
    }

    private fun autoPlay() {

        var emptyCells = ArrayList<Int>()

        for (cellId in 1..9){
            if (!player1.contains(cellId) || player2.contains(cellId)){

                emptyCells.add(cellId)
            }
        }

        val r = Random

        val randomIndex = r.nextInt(emptyCells.size)

        val CellId = emptyCells[randomIndex]

        var btnSelected: Button?

        btnSelected = when(CellId){
            1-> btn1
            2-> btn2
            3-> btn3
            4-> btn4
            5-> btn5
            6-> btn6
            7-> btn7
            8-> btn8
            9-> btn9
            else -> btn1
        }



        playGame(CellId, btnSelected)

    }

    private fun checkWinner(){
        var winner = -1

        //row 1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        // row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        // row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 2
        }

        // column 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if (player2.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 2
        }

        //column 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if (player2.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 2
        }

        // column 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 2
        }

        //diagonal left to right
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if (player2.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 2
        }

        //diagonal right to left
        if (player1.contains(7) && player1.contains(5) && player1.contains(3)){
            winner = 1
        }
        if (player2.contains(7) && player1.contains(5) && player1.contains(3)){
            winner = 2
        }

        if (winner == 1){
            Toast.makeText(this, "Player 1 has won!", Toast.LENGTH_LONG).show()


            clearBoard()

            updatePlayer1Score()



        } else if (winner == 2){
            Toast.makeText(this, "Player 2 has won!", Toast.LENGTH_LONG).show()

            clearBoard()

            updatePlayer2Score()

        }


    }

    private fun updatePlayer2Score() {

        player2Wins++

        tvPlayer2Score.text = "Player 2 Wins: $player2Wins"
    }

    private fun clearBoard() {
        player1.clear()
        player2.clear()

        allButtons.forEach{ i ->

            i.text = ""
            i.isEnabled = true
            i.setBackgroundResource(R.color.crimson)

        }




    }

    private fun updatePlayer1Score() {
        player1Wins++

        tvPlayer1Score.setText("Player 1 Wins: $player1Wins")

    }


}
