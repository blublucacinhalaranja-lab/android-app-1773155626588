package com.example.funnycalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput: StringBuilder = StringBuilder()
    private var lastResult: Double = 0.0
    private var lastOperation: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        val button0: Button = findViewById(R.id.button0)
        val button1: Button = findViewById(R.id.button1)
        val button2: Button = findViewById(R.id.button2)
        val button3: Button = findViewById(R.id.button3)
        val button4: Button = findViewById(R.id.button4)
        val button5: Button = findViewById(R.id.button5)
        val button6: Button = findViewById(R.id.button6)
        val button7: Button = findViewById(R.id.button7)
        val button8: Button = findViewById(R.id.button8)
        val button9: Button = findViewById(R.id.button9)
        val buttonDecimal: Button = findViewById(R.id.buttonDecimal)
        val buttonAdd: Button = findViewById(R.id.buttonAdd)
        val buttonSubtract: Button = findViewById(R.id.buttonSubtract)
        val buttonMultiply: Button = findViewById(R.id.buttonMultiply)
        val buttonDivide: Button = findViewById(R.id.buttonDivide)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)
        val buttonClear: Button = findViewById(R.id.buttonClear)

        button0.setOnClickListener { appendNumber("0") }
        button1.setOnClickListener { appendNumber("1") }
        button2.setOnClickListener { appendNumber("2") }
        button3.setOnClickListener { appendNumber("3") }
        button4.setOnClickListener { appendNumber("4") }
        button5.setOnClickListener { appendNumber("5") }
        button6.setOnClickListener { appendNumber("6") }
        button7.setOnClickListener { appendNumber("7") }
        button8.setOnClickListener { appendNumber("8") }
        button9.setOnClickListener { appendNumber("9") }
        buttonDecimal.setOnClickListener { appendNumber(".") }

        buttonAdd.setOnClickListener { performOperation('+') }
        buttonSubtract.setOnClickListener { performOperation('-') }
        buttonMultiply.setOnClickListener { performOperation('*') }
        buttonDivide.setOnClickListener { performOperation('/') }

        buttonEquals.setOnClickListener { calculateResult() }
        buttonClear.setOnClickListener { clearInput() }
    }

    private fun appendNumber(number: String) {
        currentInput.append(number)
        updateResultTextView()
    }

    private fun performOperation(operation: Char) {
        if (currentInput.isNotEmpty()) {
            calculateResult()
        }
        lastOperation = operation
        if (resultTextView.text.toString() != "0") {
            lastResult = resultTextView.text.toString().toDouble()
        }
        currentInput.clear()
        updateResultTextView()
    }

    private fun calculateResult() {
        if (currentInput.isNotEmpty() && lastOperation != null) {
            val currentNumber = currentInput.toString().toDouble()

            // Checagem "engraçada"
            if (lastResult == 1.0 && currentNumber == 1.0 && lastOperation == '+') {
                launchBannedActivity()
                return
            }

            when (lastOperation) {
                '+' -> lastResult += currentNumber
                '-' -> lastResult -= currentNumber
                '*' -> lastResult *= currentNumber
                '/' -> {
                    if (currentNumber != 0.0) {
                        lastResult /= currentNumber
                    } else {
                        resultTextView.text = "Error"
                        return
                    }
                }
            }

            resultTextView.text = lastResult.toString()
            currentInput.clear()
            lastOperation = null
        } else if (currentInput.isNotEmpty()) {
            lastResult = currentInput.toString().toDouble()
            resultTextView.text = lastResult.toString()
            currentInput.clear()
        }
    }

    private fun clearInput() {
        currentInput.clear()
        lastResult = 0.0
        lastOperation = null
        resultTextView.text = "0"
    }

    private fun updateResultTextView() {
        resultTextView.text = currentInput.toString().ifEmpty { "0" }
    }

    private fun launchBannedActivity() {
        val intent = Intent(this, BannedActivity::class.java)
        startActivity(intent)
        finish() // Encerra a MainActivity para evitar que o usuário volte facilmente.
    }
}