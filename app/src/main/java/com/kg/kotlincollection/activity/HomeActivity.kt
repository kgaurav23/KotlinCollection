package com.kg.kotlincollection.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kg.kotlincollection.R

class HomeActivity : AppCompatActivity() {

    private lateinit var btnFindMyAge: Button
    private lateinit var btnTicTacToe: Button
    private lateinit var btnCalculator: Button
    private lateinit var btnPokemon: Button
    private lateinit var btnZoo: Button
    private lateinit var btnRestaurant: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initViews()

        //setup click listeners to open respective screens
        setupClickListenerForFindMyAge()
        setupClickListenerForTicTacToe()
        setupClickListenerForCalculator()
        setupClickListenerForPokemon()
        setupClickListenerForZoo()
        setupClickListenerForRestaurant()
    }

    private fun setupClickListenerForRestaurant() {

    }

    private fun setupClickListenerForZoo() {

    }

    private fun setupClickListenerForPokemon() {
        btnPokemon.setOnClickListener {
            val intent = Intent(this, PokemonActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupClickListenerForCalculator() {
        btnCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupClickListenerForTicTacToe() {
        btnTicTacToe.setOnClickListener {
            val intent = Intent(this, TicTacToeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupClickListenerForFindMyAge() {
        btnFindMyAge.setOnClickListener {
            val intent = Intent(this, FindMyAgeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViews() {
        btnFindMyAge = findViewById(R.id.btn_find_my_age)
        btnTicTacToe = findViewById(R.id.btn_tic_tac_toe)
        btnCalculator = findViewById(R.id.btn_calculator)
        btnPokemon = findViewById(R.id.btn_pokemon)
        btnZoo = findViewById(R.id.btn_zoo)
        btnRestaurant = findViewById(R.id.btn_restaurant)
    }
}
