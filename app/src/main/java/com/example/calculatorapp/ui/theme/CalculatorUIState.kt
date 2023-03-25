package com.example.calculatorapp.ui.theme

data class CalculatorUIState(
    val expression:String = "0",
    val finalAns:String = "0",
    val point:Boolean = false,
    val flag:Boolean = false,
    val special:String = ""
)
