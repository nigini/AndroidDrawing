package com.example.drawing

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.translate


@Composable
fun DrawingColorChanger() {
    val colors = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary
    )
    var clickCounter by remember { mutableStateOf(0) }

    Canvas(modifier = Modifier
        .fillMaxSize()
        .background(colors[(clickCounter) % 3])
        .clickable {
            clickCounter += 1
            Log.i("STATE", "Clicks counter: ${clickCounter}")
        }
    ) {
        val sqrSize1 = size.height * .4f
        val sqrSize2 = size.height * .2f
        translate(
            left = -sqrSize1 / 2f,
            top = -sqrSize1 / 2f
        ) {
            drawRect(
                color = colors[(clickCounter + 2) % 3],
                topLeft = Offset(size.width / 2f, size.height / 2f),
                size = Size(sqrSize1, sqrSize1)
            )
        }
        translate(
            left = -sqrSize2 / 2f,
            top = -sqrSize2 / 2f
        ) {
            drawRect(
                color = colors[(clickCounter + 1) % 3],
                topLeft = Offset(size.width / 2f, size.height / 2f),
                size = Size(sqrSize2, sqrSize2)
            )
        }
    }
}