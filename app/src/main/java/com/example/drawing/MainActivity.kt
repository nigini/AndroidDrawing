package com.example.drawing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import com.example.drawing.ui.theme.DrawingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawingTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyClickableSquare(
                        color1 = MaterialTheme.colorScheme.primary,
                        color2 = MaterialTheme.colorScheme.secondary,
                        sizeAsScreenFraction = .2f,
                        positionXAsScreenFraction = .5f,
                        positionYAsScreenFraction = .5f
                    )
                    MyClickableSquare(
                        color1 = MaterialTheme.colorScheme.primary,
                        color2 = MaterialTheme.colorScheme.secondary,
                        sizeAsScreenFraction = .2f,
                        positionXAsScreenFraction = .1f,
                        positionYAsScreenFraction = .1f
                    )
                }
            }
        }
    }
}

@Composable
fun MyClickableSquare(sizeAsScreenFraction: Float = .5f, color1: Color = Color.Blue, color2: Color = Color.Red,
           positionXAsScreenFraction: Float = .5f, positionYAsScreenFraction: Float = .5f) {
    var shapeColor = color1
    var shapeSize = 0f
    Canvas(modifier = Modifier
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DrawingTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyClickableSquare(
                positionXAsScreenFraction = .5f,
                positionYAsScreenFraction = .5f
            )
        }
    }
}