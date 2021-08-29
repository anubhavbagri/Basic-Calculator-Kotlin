package com.example.basiccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun allClearTap(view: android.view.View) {}
    fun backspaceTap(view: android.view.View) {}
    fun equalsTap(view: android.view.View) {}
}