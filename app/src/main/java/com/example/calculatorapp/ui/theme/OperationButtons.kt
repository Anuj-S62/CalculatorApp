package com.example.calculatorapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OperationButton(
    symbol: String,
    modifier: Modifier = Modifier,
    onPressButton:()->Unit = {}
){
    if(symbol=="="){
        Box(modifier = Modifier
            .clip(CircleShape)
            .background(Color(255, 120, 0))
            .clickable(onClick = onPressButton)
            .then(
                modifier.size(80.dp)
            ), contentAlignment = Alignment.Center,
        )
        {
            Text(text = symbol, fontSize = 35.sp)
        }
    }
    else if(symbol=="⌫"){
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onPressButton)
                .then(
                    modifier.size(80.dp)
                ),
            contentAlignment = Alignment.Center,
        )
        {
            Text(text = symbol, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold, color = Color(255, 120, 0))
        }
    }
    else if(symbol=="↩"){
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onPressButton)
                .then(
                    modifier.size(80.dp)
                ),
            contentAlignment = Alignment.TopCenter,
        )
        {
            Text(text = symbol, fontSize = 50.sp, fontWeight = FontWeight.ExtraBold, color = Color(255, 120, 0))
        }
    }
    else {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onPressButton)
                .then(
                    modifier.size(80.dp)
                ),
            contentAlignment = Alignment.Center,
        )
        {
            Text(text = symbol, fontSize = 35.sp, color = Color(255, 120, 0))
        }
    }
}

