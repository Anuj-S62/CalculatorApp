package com.example.calculatorapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    calculatorViewModel:CalculatorViewModel = viewModel()
){
        val calculatorUiState by calculatorViewModel.uiState.collectAsState()
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),Arrangement.SpaceEvenly)
            {
                var spec = calculatorUiState.special
                var ans: String = calculatorUiState.expression
                Box(modifier = Modifier.fillMaxSize().padding(10.dp)) {
                    Text(
                        text = spec, modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.TopCenter), fontSize = 45.sp
                    )
                    Text(
                        text = ans, modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.BottomEnd), fontSize = 45.sp
                    )
                }
            }
            Row(modifier = Modifier.fillMaxWidth(),Arrangement.SpaceEvenly) {
                OperationButton(symbol = "AC", onPressButton =  { calculatorViewModel.operation("AC") })
                OperationButton(symbol="⌫") { calculatorViewModel.operation("⌫") }
                OperationButton(symbol = "↩") { calculatorViewModel.operation("↩") }
                Button(symbol = "/", onButtonPress = {calculatorViewModel.getNumber("/")})
            }
//            Spacer(Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth(),Arrangement.SpaceEvenly) {
                Button(symbol = "7", onButtonPress = {
                    calculatorViewModel.getNumber(symbol = "7")
                })
                Button(symbol = "8", onButtonPress = {
                    calculatorViewModel.getNumber("8")
                })
                Button(symbol = "9", onButtonPress = {
                    calculatorViewModel.getNumber("9")
                })
                Button(symbol = "×", onButtonPress = {calculatorViewModel.getNumber("×")})

            }
//            Spacer(Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth(),Arrangement.SpaceEvenly) {
                Button(symbol = "4", onButtonPress = {
                    calculatorViewModel.getNumber("4")
                })
                Button(symbol = "5", onButtonPress = {
                    calculatorViewModel.getNumber("5")
                })
                Button(symbol = "6", onButtonPress = {
                    calculatorViewModel.getNumber("6")
                })
                Button(symbol = "-", onButtonPress = {calculatorViewModel.getNumber("-")})
            }
//            Spacer(Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth(),Arrangement.SpaceEvenly) {
                Button(symbol = "1", onButtonPress = {
                    calculatorViewModel.getNumber("1")
                })
                Button(symbol = "2", onButtonPress = {
                    calculatorViewModel.getNumber("2")
                })
                Button(symbol = "3", onButtonPress = {
                    calculatorViewModel.getNumber("3")
                })
                Button(symbol = "+", onButtonPress = {calculatorViewModel.getNumber("+")})
            }
//            Spacer(Modifier.height(2.dp))
            Row(modifier = Modifier.fillMaxWidth(),Arrangement.SpaceEvenly) {
                SpecialButton(symbol = "ℛ") { calculatorViewModel.action("𝘼𝙣𝙪𝙟") }
                Button(symbol = "0", onButtonPress = {calculatorViewModel.getNumber("0")})
                Button(symbol = ".",onButtonPress = {calculatorViewModel.getNumber(".")})
                OperationButton(symbol = "=") { calculatorViewModel.operation("=") }
            }
//            Spacer(modifier = Modifier.height(.dp))
        }

    }
