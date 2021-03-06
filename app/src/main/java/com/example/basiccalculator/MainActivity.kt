package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var isNewOp=true
    var dot=false
    var signChange = true
    fun buNumberEvent(view: View)
    {
        if(isNewOp)
        {
            workingTextView.text = ""
        }
        isNewOp=false
        val buSelect= view as Button
        var buClickValue:String=workingTextView.text.toString()
        when(buSelect.id)
        {
            bu0.id->
            {
                buClickValue+="0"
            }
            bu1.id->
            {
                buClickValue+="1"
            }
            bu2.id->
            {
                buClickValue+="2"
            }
            bu3.id->
            {
                buClickValue+="3"
            }
            bu4.id->
            {
                buClickValue+="4"
            }
            bu5.id->
            {
                buClickValue+="5"
            }
            bu6.id->
            {
                buClickValue+="6"
            }
            bu7.id->
            {
                buClickValue+="7"
            }
            bu8.id->
            {
                buClickValue+="8"
            }
            bu9.id->
            {
                buClickValue+="9"
            }
            buDot.id->
            {
                if(!dot)
                {
                    buClickValue += "."
                }
                dot=true
            }
            buPlusMinus.id->
            {
                if(signChange){
                    buClickValue= "-$buClickValue"
                }
                signChange = false
            }
        }
        workingTextView.text = buClickValue
    }
    var op="×"
    var oldNumber=""

    fun buOpEvent(view: View)
    {
        val buSelect= view as Button
        when(buSelect.id)
        {
            buMul.id->
            {
                op="×"
            }
            buDiv.id->
            {
                op="÷"
            }
            buSub.id->
            {
                op="-"
            }
            buSum.id->
            {
                op="+"
            }
        }
        oldNumber=workingTextView.text.toString()
        isNewOp=true
        dot=false
    }

    fun buEqualEvent(view: View)
    {
        val newNumber=workingTextView.text.toString()
        var finalNumber:Double?=null
        when(op)
        {
            "×"->
            {
                finalNumber=oldNumber.toDouble() * newNumber.toDouble()
            }
            "÷"->
            {
                finalNumber=oldNumber.toDouble() / newNumber.toDouble()
            }
            "-"->
            {
                finalNumber=oldNumber.toDouble() - newNumber.toDouble()
            }
            "+"->
            {
                finalNumber=oldNumber.toDouble() + newNumber.toDouble()
            }
        }
        workingTextView.text = finalNumber.toString()
        isNewOp=true
    }

    fun buPercentEvent(view: View)
    {
        val number=(workingTextView.text.toString().toDouble())/100
        workingTextView.text = number.toString()
        isNewOp=true
    }

    fun buCleanEvent(view: View)
    {
        workingTextView.text = ""
        isNewOp=true
        dot=false
    }
}