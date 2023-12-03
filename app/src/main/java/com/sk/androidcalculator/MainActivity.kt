package com.sk.androidcalculator

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
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

        var numCount = 0

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
        val btnModule: Button = findViewById(R.id.btnModule)

        val resultView: TextView = findViewById(R.id.resultView)
        val historyView: TextView = findViewById(R.id.historyView)

        val btnClean: Button = findViewById(R.id.btnClean)
        val btnCleanAll: Button = findViewById(R.id.btnCleanAll)

        val btnDecimal: Button = findViewById(R.id.btnDecimal)

        val btnNegate: Button = findViewById(R.id.btnNegate)

        /**
         * Enables the buttons for addition, subtraction, multiplication, division, and square root.
         */
        fun activateButtons() {
            btnPlus.isEnabled = true
            btnMinus.isEnabled = true
            btnMultiply.isEnabled = true
            btnDivide.isEnabled = true
            btnSqrt.isEnabled = true
        }

        /**
         * Disables the buttons for addition, subtraction, multiplication, division, and square root.
         */
        fun deactivateButtons() {
            btnPlus.isEnabled = false
            btnMinus.isEnabled = false
            btnMultiply.isEnabled = false
            btnDivide.isEnabled = false
            btnSqrt.isEnabled = false
        }

        /**
         * Sets the text size of the result view to the default size.
         */
        fun defaultSize() {
            resultView.textSize = 33F
        }

        /**
         * Clears the history view.
         */
        fun clearHistory() {
            historyView.text = ""
        }

        fun clearResult() {
            resultView.text = "0"
        }

        /**
         * Resets the value of `numCount` to 0.
         */
        fun resetNumCount() {
            numCount = 0
        }

        fun limitReachedError() {
            Toast.makeText(
                this,
                "You have reached the maximum number of digits.",
                Toast.LENGTH_SHORT
            ).show()
        }

        fun copiedMsg() {
            Toast.makeText(this, "Copied to clipboard", Toast.LENGTH_SHORT).show()
        }

        fun enterNumberFirstError() {
            Toast.makeText(this, "Enter a number first", Toast.LENGTH_SHORT).show()
        }

        fun negativeNumberError() {
            Toast.makeText(this, "The number cannot be negative.", Toast.LENGTH_SHORT).show()
        }

        fun twoNumbersError() {
            Toast.makeText(this, "You must write 2 numbers.", Toast.LENGTH_SHORT).show()
        }

        /**
         * Calculates the module of a given number.
         * The module is calculated by dividing the number by 100.
         * The result is displayed in the resultView.
         * The calculation history is displayed in the historyView.
         * Resets the number count.
         *
         * @param number The number to calculate the module for.
         */
        fun calculateModule(number: Double) {
            val result = number / 100
            resultView.text = result.toString()
            historyView.text = number.toInt().toString() + "% = " + result
            resetNumCount()
        }

        /**
         * Calculates the square root of a given number and updates the result view and history view.
         *
         * @param number The number to calculate the square root of.
         */
        fun calculateSqrt(number: Double) {
            val result = Math.sqrt(number)
            resultView.text = result.toString()
            historyView.text = "√" + number.toInt() + " = " + result
            resetNumCount()
        }


        /**
         * Updates the result view with the given number at the specified index.
         *
         * @param currentText The current text displayed in the result view.
         * @param index The index of the number to be added.
         * @param numCount The count of numbers currently displayed in the result view.
         */
        fun putNumber(currentText: String, index: Int, numCount: Int) {
            if (currentText == "0") {
                resultView.text = index.toString()
            } else {
                if (numCount == 7) {
                    resultView.textSize = 29F
                }
                if (numCount >= 10) {
                    limitReachedError()
                } else {
                    resultView.text = currentText + index
                }
            }
        }

        /**
         * Checks if an operation has been done and updates the history view accordingly.
         *
         * @param operationDone Indicates whether an operation has been performed.
         * @param text The text to be displayed in the history view.
         */
        fun operationCheck(operationDone: Boolean, text: String) {
            if (!operationDone) {
                twoNumbersError()
                clearHistory()
            } else {
                historyView.text = text + " = " + resultView.text
            }
        }


        /**
         * This code initializes a list of buttons in the MainActivity class.
         * The buttons are assigned to the corresponding views in the layout file.
         * The list contains the following buttons:
         * - btn0 to btn9: Numeric buttons representing digits 0 to 9.
         * - btnEqual: Button for performing the equal operation.
         * - btnPlus: Button for performing addition operation.
         * - btnMinus: Button for performing subtraction operation.
         * - btnMultiply: Button for performing multiplication operation.
         * - btnDivide: Button for performing division operation.
         * - btnSqrt: Button for performing square root operation.
         * - btnClean: Button for clearing the current input.
         * - btnCleanAll: Button for clearing all input and operations.
         * - btnDecimal: Button for adding decimal point to the input.
         * - btnNegate: Button for negating the current input.
         */
        val buttons = listOf(
            btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnEqual, btnPlus, btnMinus, btnMultiply, btnDivide, btnSqrt,
            btnClean, btnCleanAll, btnDecimal, btnNegate
        )

        /**
         * Iterates through a list of buttons and sets an onClickListener for each button.
         * It increments the `numCount` variable and retrieves the current text from `resultView`.
         * Then, it calls the `putNumber` function with the current text, the index of the button, and the updated `numCount`.
         */
        for ((index, button) in buttons.withIndex()) {
            button.setOnClickListener {
                numCount++
                val currentText = resultView.text.toString()

                putNumber(currentText, index, numCount)
            }
        }

        /**
         * This code block handles the click event for the resultView and btnClean buttons.
         * - When the resultView is clicked and its text is not "0", it copies the text to the clipboard and displays a toast message.
         * - When the btnClean button is clicked, it removes the last character from the resultView text and updates the numCount variable.
         *   If the resultView text becomes empty or has only one character, it sets the text to "0", resets the UI state, enables the btnDecimal button, and resets the numCount variable.
         */
        resultView.setOnClickListener {
            val text = resultView.text.toString()
            if (text != "0") {
                copiedMsg()
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("label", text)
                clipboard.setPrimaryClip(clip)
            }
        }

        /**
         * This code block contains event handlers for the "Clean", "Clean All", and "Plus" buttons in the MainActivity.kt file.
         *
         * - The "Clean" button removes the last character from the resultView text if it is not empty. If the resultView text becomes empty or contains only one character, it sets the text to "0", resets the button states, enables the decimal button, and resets the numCount variable.
         *
         * - The "Clean All" button sets the resultView text to "0", clears the history, resets the button states, enables the decimal button, and resets the numCount variable.
         *
         * - The "Plus" button appends " + " to the resultView text if it is not "0". It deactivates the buttons, enables the decimal button, and resets the numCount variable.
         */
        btnClean.setOnClickListener {
            val text = resultView.text.toString()
            if (text.isNotEmpty()) {
                resultView.text = text.substring(0, text.length - 1)
                numCount--
            }
            if (resultView.text.isEmpty() || resultView.text.length == 1) {
                resultView.text = "0"
                defaultSize()
                activateButtons()
                btnDecimal.isEnabled = true
                resetNumCount()
            }
        }

        /**
         * This code block handles the click events for the "Clean All", "Plus", and "Minus" buttons.
         *
         * - When the "Clean All" button is clicked, it calls the functions clearResult(), clearHistory(), defaultSize(), activateButtons(), resetNumCount(), and enables the "Decimal" button.
         * - When the "Plus" button is clicked, it checks if the resultView text is "0". If it is, it displays a toast message asking the user to enter a number first. Otherwise, it appends " + " to the resultView text, deactivates the buttons, enables the "Decimal" button, and resets the number count.
         * - When the "Minus" button is clicked, it checks if the resultView text is "0". If it is, it displays a toast message asking the user to enter a number first. Otherwise, it appends " - " to the resultView text, deactivates the buttons, enables the "Decimal" button, and resets the number count.
         */
        btnCleanAll.setOnClickListener {
            clearResult()
            clearHistory()
            defaultSize()
            activateButtons()
            btnDecimal.isEnabled = true
            resetNumCount()
        }

        /**
         * This code block handles the click events for the plus, minus, and multiply buttons.
         * If the resultView text is "0", it displays a toast message asking the user to enter a number first.
         * Otherwise, it appends the corresponding operator ("+", "-", or "×") to the resultView text.
         * It also deactivates the buttons, enables the decimal button, and resets the number count.
         */
        btnPlus.setOnClickListener {
            if (resultView.text == "0") {
                enterNumberFirstError()
            } else {
                resultView.text = resultView.text.toString() + " + "
                deactivateButtons()
                btnDecimal.isEnabled = true
                resetNumCount()
            }
        }

        /**
         * This code block is responsible for handling the click event of the "Minus" button.
         * If the resultView text is "0", it displays a toast message asking the user to enter a number first.
         * Otherwise, it appends " - " to the resultView text, deactivates the buttons, enables the decimal button,
         * and resets the number count.
         */
        btnMinus.setOnClickListener {
            if (resultView.text == "0") {
                enterNumberFirstError()

            } else {
                resultView.text = resultView.text.toString() + " - "
                deactivateButtons()
                btnDecimal.isEnabled = true
                resetNumCount()
            }
        }

        /**
         * This code block is responsible for handling the click event of the "Multiply" button.
         * If the current value in the resultView is "0", it displays a toast message asking the user to enter a number first.
         * Otherwise, it appends " × " to the current value in the resultView, deactivates the buttons, enables the decimal button,
         * and resets the number count.
         */
        btnMultiply.setOnClickListener {
            if (resultView.text == "0") {
                enterNumberFirstError()

            } else {
                resultView.text = resultView.text.toString() + " × "
                deactivateButtons()
                btnDecimal.isEnabled = true
                resetNumCount()
            }
        }

        /**
         * This function is called when the divide button is clicked.
         * It checks if the resultView text is "0" and displays a toast message if true.
         * Otherwise, it appends " ÷ " to the resultView text, deactivates buttons, enables the decimal button, and resets the number count.
         */
        btnDivide.setOnClickListener {
            if (resultView.text == "0") {
                enterNumberFirstError()
            } else {
                resultView.text = resultView.text.toString() + " ÷ "
                deactivateButtons()
                btnDecimal.isEnabled = true
                resetNumCount()
            }
        }

        /**
         * This function is called when the "btnSqrt" button is clicked.
         * It retrieves the number from the "resultView" and checks if it is a valid number.
         * If the number is valid and greater than or equal to 0, it calls the "calculateSqrt" function.
         * If the number is not valid or negative, it displays a toast message indicating that the number cannot be negative.
         */
        btnSqrt.setOnClickListener {
            val number = resultView.text.toString().toDoubleOrNull()
            if (number != null) {
                if (number >= 0) {
                    calculateSqrt(number)
                } else {
                    negativeNumberError()
                }
            } else {
                enterNumberFirstError()
            }
        }

        /**
         * This function is called when the "btnModule" button is clicked.
         * It checks if the "resultView" text is "0" and displays a toast message if it is.
         * Otherwise, it converts the text in "resultView" to a Double and calls the "calculateModule" function with that number.
         * After the calculation, it resets the number count.
         */
        btnModule.setOnClickListener {
            if (resultView.text == "0") {
                enterNumberFirstError()
            } else {
                val number = resultView.text.toString().toDoubleOrNull()
                calculateModule(number!!)
                resetNumCount()
            }
        }

        /**
         * Handles the click event of the decimal button.
         * If the result view text is "0", it displays a toast message indicating that a number must be written.
         * Otherwise, it appends a decimal point to the result view text and disables the decimal button.
         */
        btnDecimal.setOnClickListener {
            if (resultView.text == "0") {
                enterNumberFirstError()
            } else {
                resultView.text = resultView.text.toString() + "."
                btnDecimal.isEnabled = false
            }
        }

        /**
         * This function is called when the negate button is clicked.
         * It negates the value displayed in the resultView by adding or removing a negative sign.
         */
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

        /**
         * This code block represents the click event listener for the "Equal" button in the MainActivity.kt file.
         * It performs the calculation based on the text entered in the resultView and updates the resultView with the calculated result.
         * The calculation is performed by splitting the text using various operators (+, -, ÷, ×, ^, %) and then evaluating the expression.
         * If the expression is valid and the calculation is successful, the result is displayed in the resultView.
         * If the expression is invalid or the calculation fails, the resultView remains unchanged.
         * Additionally, this code block calls the operationCheck() function to handle any additional operations after the calculation is performed.
         */
        btnEqual.setOnClickListener {
            activateButtons()
            defaultSize()
            btnDecimal.isEnabled = true
            resetNumCount()

            val text = resultView.text.toString()
            val operators = listOf("+", "-", "÷", "×", "^", "%")

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
                                "÷" -> num1 / num2
                                "×" -> num1 * num2
                                "%" -> num1 % num2
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

            operationCheck(operationDone, text)
        }
    }
}