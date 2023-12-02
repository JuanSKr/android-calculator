package com.sk.androidcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        val btnEqual: Button = findViewById(R.id.btnEqual)
        val btnPlus: Button = findViewById(R.id.btnSuma)
        val btnMinus: Button = findViewById(R.id.btnMinus)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btnSqrt: Button = findViewById(R.id.btnSqrt)
        val btnPower: Button = findViewById(R.id.btnPower)

        val resultView: TextView = findViewById(R.id.resultView)
        val historyView: TextView = findViewById(R.id.historyView)

        val btnClean: Button = findViewById(R.id.btnClean)
        val btnCleanAll: Button = findViewById(R.id.btnCleanAll)

        val btnDecimal: Button = findViewById(R.id.btnDecimal)

        val btnNegate: Button = findViewById(R.id.btnNegate)

        fun activateButtons() {
            btnPlus.isEnabled = true
            btnMinus.isEnabled = true
            btnMultiply.isEnabled = true
            btnDivide.isEnabled = true
            btnSqrt.isEnabled = true
            btnPower.isEnabled = true
        }

        fun deactivateButtons() {
            btnPlus.isEnabled = false
            btnMinus.isEnabled = false
            btnMultiply.isEnabled = false
            btnDivide.isEnabled = false
            btnSqrt.isEnabled = false
            btnPower.isEnabled = false
        }

        fun defaultSize() {
            resultView.textSize = 35F
        }

        val buttons = listOf(
            btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnEqual, btnPlus, btnMinus, btnMultiply, btnDivide, btnSqrt, btnPower,
            btnClean, btnCleanAll, btnDecimal, btnNegate
        )

        for ((index, button) in buttons.withIndex()) {
            button.setOnClickListener {
                val currentText = resultView.text.toString()
                val operatorIndex = currentText.indexOfAny(charArrayOf('+', '-', '×', '÷', '^'))
                if (operatorIndex != -1 && currentText.length - operatorIndex > 10) {
                    Toast.makeText(
                        this,
                        "Maximum number of digits reached",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    if (currentText == "0") {
                        resultView.text = index.toString()
                    } else {
                        if (currentText.length == 9) {
                            Toast.makeText(
                                this,
                                "Maximum number of digits reached",
                                Toast.LENGTH_SHORT
                            ).show()
                            resultView.textSize = 27F
                        } else {
                            resultView.text = currentText + index
                        }
                    }
                }
            }
        }

        btnClean.setOnClickListener {
            val text = resultView.text.toString()
            if (text.isNotEmpty()) {
                resultView.text = text.substring(0, text.length - 1)
            }
            if (resultView.text.isEmpty() || resultView.text.length == 1) {
                resultView.text = "0"
                defaultSize()
                activateButtons()
                btnDecimal.isEnabled = true
            }
        }

        btnCleanAll.setOnClickListener {
            resultView.text = "0"
            historyView.text = ""
            defaultSize()
            activateButtons()
            btnDecimal.isEnabled = true
        }

        btnPlus.setOnClickListener {
            if (resultView.text == "0") {
                Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()

            } else {
                resultView.text = resultView.text.toString() + " + "
                deactivateButtons()
                btnDecimal.isEnabled = true
            }
        }

        btnMinus.setOnClickListener {
            if (resultView.text == "0") {
                Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()

            } else {
                resultView.text = resultView.text.toString() + " - "
                deactivateButtons()
                btnDecimal.isEnabled = true
            }
        }

        btnMultiply.setOnClickListener {
            if (resultView.text == "0") {
                Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()

            } else {
                resultView.text = resultView.text.toString() + " × "
                deactivateButtons()
                btnDecimal.isEnabled = true
            }
        }

        btnDivide.setOnClickListener {
            if (resultView.text == "0") {
                Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()

            } else {
                resultView.text = resultView.text.toString() + " ÷ "
                deactivateButtons()
                btnDecimal.isEnabled = true
            }
        }

        btnSqrt.setOnClickListener {
            val number = resultView.text.toString().toDoubleOrNull()
            if (number != null) {
                if (number >= 0) {
                    val result = Math.sqrt(number)
                    resultView.text = result.toString()
                    resultView.text = "√" + number.toInt() + " = " + result
                } else {
                    Toast.makeText(this, "The number cannot be negative.", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "The number cannot be negative.", Toast.LENGTH_SHORT).show()
            }
        }

        btnPower.setOnClickListener {
            if (resultView.text == "0") {
                Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()

            } else {
                resultView.text = resultView.text.toString() + " ^ "
                deactivateButtons()
                btnDecimal.isEnabled = true
            }
        }

        btnDecimal.setOnClickListener {
            if (resultView.text == "0") {
                Toast.makeText(this, "You must write a number.", Toast.LENGTH_SHORT).show()
            } else {
                resultView.text = resultView.text.toString() + "."
                btnDecimal.isEnabled = false
            }
        }

        btnNegate.setOnClickListener {
            val text = resultView.text.toString()
            if (text.isNotEmpty()) {
                if (text[0] == '-') {
                    resultView.text = text.substring(1)
                } else {
                    resultView.text = "-$text"
                }
            }
        }

        btnEqual.setOnClickListener {
            activateButtons()
            btnDecimal.isEnabled = true

            val text = resultView.text.toString()
            val operators = listOf("+", "-", "/", "*", "^")

            var operationDone = false

            for (operator in operators) {
                if (text.contains(operator)) {
                    val parts = text.split(operator)
                    if (parts.size == 2) {
                        val num1 = parts[0].toDoubleOrNull()
                        val num2 = parts[1].toDoubleOrNull()

                        if (num1 != null && num2 != null) {
                            val result = when (operator) {
                                "+" -> num1 + num2
                                "-" -> num1 - num2
                                "/" -> num1 / num2
                                "*" -> num1 * num2
                                "^" -> Math.pow(num1, num2)
                                else -> null
                            }
                            if (result != null) {
                                resultView.text = result.toString()
                                operationDone = true
                            }
                        }

                    }
                }
            }

            if (!operationDone) {
                Toast.makeText(this, "You must write 2 numbers.", Toast.LENGTH_SHORT).show()
                historyView.text = ""
            } else {
                historyView.text = text + " = " + resultView.text
            }
        }
    }
}