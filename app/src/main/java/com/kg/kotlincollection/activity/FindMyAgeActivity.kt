package com.kg.kotlincollection.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kg.kotlincollection.R
import java.util.*

class FindMyAgeActivity : AppCompatActivity() {

    private lateinit var etDOB: EditText
    private lateinit var tvAge: TextView
    private lateinit var btnGetAge: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_my_age)
        initViews()

        btnGetAge.setOnClickListener {
            onButtonGetAgeClick()
        }
    }

    private fun initViews() {
        etDOB = findViewById(R.id.etDOB)
        tvAge = findViewById(R.id.tvAge)
        btnGetAge = findViewById(R.id.btnGetAge)
    }

    private fun onButtonGetAgeClick() {
        val userDOB = Integer.parseInt(etDOB.text.toString())
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)

        val userAgeInYears = currentYear - userDOB
        tvAge.text = getString(R.string.age_text, userAgeInYears)
    }
}
