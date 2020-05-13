package com.kg.kotlincollection.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kg.kotlincollection.R
import kotlinx.android.synthetic.main.activity_calculator.*

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
    }

    fun buNumberEvent(view: View) {
        if(isNewOp) {
            etCalculationArea.setText("")
            isNewOp = false
        }
        val buttonSelected = view as Button

        val previousValue: String = etCalculationArea.text.toString()
        var currentValue = when (buttonSelected.id) {
            R.id.bu0 -> previousValue+"0"
            R.id.bu1 -> previousValue+"1"
            R.id.bu2 -> previousValue+"2"
            R.id.bu3 -> previousValue+"3"
            R.id.bu4 -> previousValue+"4"
            R.id.bu5 -> previousValue+"5"
            R.id.bu6 -> previousValue+"6"
            R.id.bu7 -> previousValue+"7"
            R.id.bu8 -> previousValue+"8"
            R.id.bu9 -> previousValue+"9"
            R.id.budot -> "$previousValue."
            R.id.buplusminus -> "-$previousValue"
            else -> ""
        }

        etCalculationArea.setText(currentValue)
    }

    fun buACEvent(view: View) {
        isNewOp = true
        etCalculationArea.setText("0")
    }

    fun buPercent(view: View) {
        isNewOp = true
        val number = etCalculationArea.text.toString().toDouble() / 100
        etCalculationArea.setText(number.toString())
    }

    var op = "*"
    var oldNumber = ""
    var isNewOp = true
    fun buOperatorEvent(view: View) {

        when(view.id) {
            budiv.id -> {
                op = "/"
            }
            bumult.id -> {
                op = "*"
            }
            buminus.id -> {
                op = "-"
            }
            buplus.id -> {
                op = "+"
            }
        }

        oldNumber = etCalculationArea.text.toString()
        isNewOp = true
    }

    fun buEqualsEvent(view: View) {
        val newNumber = etCalculationArea.text.toString()
        var result: Double? = null

        when(op) {
            "/" -> {
                result = oldNumber.toDouble()  / newNumber.toDouble()
            }
            "*" -> {
                result = oldNumber.toDouble()  * newNumber.toDouble()
            }
            "-" -> {
                result = oldNumber.toDouble()  - newNumber.toDouble()
            }
            "+" -> {
                result = oldNumber.toDouble()  + newNumber.toDouble()
            }
        }

        etCalculationArea.setText(result.toString())
        isNewOp = true
    }
}
