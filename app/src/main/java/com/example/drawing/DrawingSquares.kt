package com.example.drawing

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp


@Composable
fun DrawingSquaresWithSpaces() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp)
                .clip(shape = MaterialTheme.shapes.extraLarge)
                .background(MaterialTheme.colorScheme.primary)
        )
        Spacer(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(MaterialTheme.colorScheme.onPrimary)
        )
    }
}


@Composable
fun DrawingSquaresWithCanvas(colors: ColorScheme = MaterialTheme.colorScheme) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val sqrSize = size.height * .2f
        val center = Offset(size.width/2f, size.height/2f)
        translate (left = -sqrSize/2f, -sqrSize/2f){
            drawRect(
                colors.primary,
                size = Size(sqrSize, sqrSize),
                topLeft = center
            )
        }
        val sqrSize2 = size.height * .1f
        translate (left = -sqrSize2/2f, -sqrSize2/2f){
            drawRect(
                colors.onPrimary,
                size = Size(sqrSize2, sqrSize2),
                topLeft = center
            )
        }
    }
}