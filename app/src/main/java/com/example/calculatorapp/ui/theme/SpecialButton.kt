package com.example.calculatorapp.ui.theme

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
fun SpecialButton(
    symbol:String,
    modifier: Modifier = Modifier,
    onButtonPress:()->Unit = {}

) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .clickable(onClick = onButtonPress)
                .then(
                    modifier.size(80.dp)
                ), contentAlignment = Alignment.Center
        )
        {
            Text(
                text = symbol,
                fontSize = 40.sp,
                fontWeight = FontWeight.Medium,
                color = Color(255, 120, 0)
            )
        }
}