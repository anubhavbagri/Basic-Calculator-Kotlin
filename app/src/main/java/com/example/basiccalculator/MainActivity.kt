package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private var isOperationPossible = false
    private var isPercentPossible = true
    private var isDecimalPossible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun numberTap(view: View) {
        if (view is Button) {
            if (view.text == ".") {
                if (isDecimalPossible){
                    findViewById(R.id.workingTextView).setText(view.text)
                }
                isDecimalPossible = false
            } else
                workingTextView.append(view.text)
            isOperationPossible = true
        }
    }

    fun operatorTap(view: View) {
        if (view is Button && isOperationPossible) {
            workingTextView.append(view.text)
            isOperationPossible = false
            isDecimalPossible = true
        }
    }

    fun percentTap(view: View) {
//        if (view is Button && isOperationPossible && isPercentPossible) {
//            resultTextView.text = ((view.text as Float)/100.0).toString()
//            isPercentPossible = false
////            isOperationPossible = false
//        }
    }

    fun allClearTap(view: View) {
        workingTextView.text = ""
        resultTextView.text = ""
        isPercentPossible = true
    }

    fun backspaceTap(view: View) {
        val length = workingTextView.length()
        if (length > 0) {
            workingTextView.text = workingTextView.text.subSequence(0, length - 1)
        }
    }

    fun equalsTap(view: View) {
        println("~~~~~~~~~~~~~~~~~~~~~~~~")
//        findViewById(resultTextView)
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