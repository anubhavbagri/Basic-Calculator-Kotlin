package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var isOperationPossible = false
    private var isDecimalPossible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberTap(view: android.view.View) {
        if (view is Button) {
            if (view.text == ".") {
                if (isDecimalPossible)
                    workingTextView.append(view.text)
                isDecimalPossible = false
            } else
                workingTextView.append(view.text)
            isOperationPossible = true
        }
    }

    fun operatorTap(view: android.view.View) {
        if (view is Button && isOperationPossible) {
            workingTextView.append(view.text)
            isOperationPossible = false
            isDecimalPossible = true
        }
    }

    fun allClearTap(view: android.view.View) {
        workingTextView.text = ""
        resultTextView.text = ""
    }

    fun backspaceTap(view: android.view.View) {
        val length = workingTextView.length()
        if (length > 0) {
            workingTextView.text = workingTextView.text.subSequence(0, length - 1)
        }
    }

    fun equalsTap(view: android.view.View) {
        resultTextView.text = calculateResults()
    }

    private fun calculateResults(): String {

        val digitsOperators = digitsOperators()
        if (digitsOperators.isEmpty()) return ""

        val multiplyDivide = multiplyDivideCalculation(digitsOperators)
        if (multiplyDivide.isEmpty()) return ""

        val result = additionSubtractionCalculation(multiplyDivide)
        return result.toString()
    }

    private fun additionSubtractionCalculation(passedList: MutableList<Any>): Float {
        var result = passedList[0] as Float

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex) {
                val op = passedList[i]
                val nextDigit = passedList[i + 1] as Float
                if (op == '+')
                    result += nextDigit
                if (op == '–')
                    result -= nextDigit
            }
        }
        return result
    }

    private fun multiplyDivideCalculation(passedList: MutableList<Any>): MutableList<Any> {
        var list = passedList
        while (list.contains('×') || list.contains('÷')) {
            list = calcMultiplyDivide(list)
        }
        return list
    }

    private fun calcMultiplyDivide(passedList: MutableList<Any>): MutableList<Any> {
        val newList = mutableListOf<Any>()
        var restartIndex = passedList.size

        for (i in passedList.indices) {
            if (passedList[i] is Char && i != passedList.lastIndex && i < restartIndex) {
                val operator = passedList[i]
                val prevDigit = passedList[i - 1] as Float
                val nextDigit = passedList[i + 1] as Float
                when (operator) {
                    'x' -> {
                        newList.add(prevDigit * nextDigit)
                        restartIndex = i + 1
                    }
                    '/' -> {
                        newList.add(prevDigit / nextDigit)
                        restartIndex = i + 1
                    }
                    // found addition or subtraction
                    else -> {
                        newList.add(prevDigit)
                        newList.add(operator)
                    }
                }
            }

            if (i > restartIndex)
                newList.add(passedList[i])
        }
        return newList
    }

    private fun digitsOperators(): MutableList<Any> {
        val list = mutableListOf<Any>()
        var currentDigit = ""
        for (character in workingTextView.text) {
            if (character.isDigit() || character == '.')
                currentDigit += character
            else {
                list.add(currentDigit.toFloat())
                currentDigit = ""
                list.add(character)
            }
        }

        if (currentDigit != "")
            list.add(currentDigit.toFloat())

        return list
    }

}