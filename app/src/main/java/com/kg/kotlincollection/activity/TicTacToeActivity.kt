package com.kg.kotlincollection.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kg.kotlincollection.R

class TicTacToeActivity : AppCompatActivity() {

    private var activePlayer: Int = 1
    private var player1Moves = mutableListOf<Int>()
    private var player2Moves = mutableListOf<Int>()
    private var bothPlayerMoves = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)
    }

    fun buttonClick(view: View) {

        val buttonSelected = view as Button

        val cellId = when(buttonSelected.id) {
            R.id.bu1 -> 1
            R.id.bu2 -> 2
            R.id.bu3 -> 3
            R.id.bu4 -> 4
            R.id.bu5 -> 5
            R.id.bu6 -> 6
            R.id.bu7 -> 7
            R.id.bu8 -> 8
            R.id.bu9 -> 9
            else -> 0
        }

        playGame(cellId, buttonSelected)
    }

    private fun playGame(cellId: Int, buttonSelected: Button) {
        if(activePlayer == 1) {
            buttonSelected.text = "X"
            player1Moves.add(cellId)
            buttonSelected.setBackgroundResource(R.color.tictactoe_btn_player1_color)
            activePlayer = 2
        } else {
            buttonSelected.text = "O"
            player2Moves.add(cellId)
            buttonSelected.setBackgroundResource(R.color.tictactoe_btn_player2_color)
            activePlayer = 1
        }

        buttonSelected.isEnabled = false
        checkWinner(player1Moves, player2Moves)
    }

    private fun checkWinner(
        player1Moves: MutableList<Int>,
        player2Moves: MutableList<Int>
    ) {
        var winner: Int = -1

        if(player1Moves.containsAll(listOf(1,2,3))
            || player1Moves.containsAll(listOf(4,5,6))
            || player1Moves.containsAll(listOf(4,5,6))) {
            winner = 1
        } else if (player2Moves.containsAll(listOf(1,2,3))
            || player2Moves.containsAll(listOf(4,5,6))
            || player2Moves.containsAll(listOf(4,5,6))){
            winner = 2
        } else if(player1Moves.containsAll(listOf(1,4,7))
            || player1Moves.containsAll(listOf(2,5,8))
            || player1Moves.containsAll(listOf(3,6,9))) {
            winner = 1
        } else if (player2Moves.containsAll(listOf(1,4,7))
            || player2Moves.containsAll(listOf(2,5,8))
            || player2Moves.containsAll(listOf(3,6,9))){
            winner = 2
        } else if(player1Moves.containsAll(listOf(1,5,9))
            || player1Moves.containsAll(listOf(3,5,7))) {
            winner = 1
        } else if(player2Moves.containsAll(listOf(1,5,9))
            || player2Moves.containsAll(listOf(3,5,7))) {
            winner = 2
        }

        if(winner == 1) {
            Toast.makeText(this, "Player1 wins!", Toast.LENGTH_SHORT).show()
        } else if(winner == 2) {
            Toast.makeText(this, "Player2 wins!", Toast.LENGTH_SHORT).show()
        }

        if(winner == -1 && isAllSquaresFilled()) {
            Toast.makeText(this, "It's a Draw!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isAllSquaresFilled(): Boolean {
        var isAllSquaresFilled = false
        bothPlayerMoves.clear()
        bothPlayerMoves.addAll(player1Moves)
        bothPlayerMoves.addAll(player2Moves)
        if(bothPlayerMoves.containsAll((1..9).toList())) {
            isAllSquaresFilled = true
        }

        return isAllSquaresFilled
    }
}
