package com.example.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate

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