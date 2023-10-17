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
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.unit.dp


@Composable
fun Drawing1(colors: ColorScheme = MaterialTheme.colorScheme) {
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

@Composable
fun Drawing1WithSpaces() {
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
fun MyClickableSquare(sizeAsScreenFraction: Float = .5f, color1: Color = Color.Blue, color2: Color = Color.Red,
                      positionXAsScreenFraction: Float = .5f, positionYAsScreenFraction: Float = .5f) {
    var shapeColor = color1
    var shapeSize: Float
    Canvas(modifier = Modifier
        .wrapContentSize()
        .clickable {
            shapeColor = if (shapeColor == color1) color2 else color1
        }
    ){
        shapeSize = size.width * sizeAsScreenFraction
        translate (left = -shapeSize/2, top = -shapeSize/2){
            drawRect(
                topLeft = Offset(
                    size.width * positionXAsScreenFraction,
                    size.height * positionYAsScreenFraction
                ),
                color = shapeColor,
                size = Size(shapeSize, shapeSize)
            )
        }
    }
}

@Composable
fun DrawingColorClick() {
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