package com.example.drawing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
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
                    Drawing1()
                }
            }
        }
    }
}

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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DrawingTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Drawing1()
        }
    }
}