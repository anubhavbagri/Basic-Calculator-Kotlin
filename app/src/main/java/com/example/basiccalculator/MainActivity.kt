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

    private fun calculateResults(): String{
        return  ""
    }


}