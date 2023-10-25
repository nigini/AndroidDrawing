package com.example.drawing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import com.example.drawing.pad.DrawOnClick
import com.example.drawing.ui.theme.DrawingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DrawingTheme {
                DrawOnClick()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DrawingTheme {
        var angle by remember { mutableStateOf(0)} //No remember required!!!
        var sqrSize = 300f
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clickable {
                    angle += 20
                }
        ) {
            rotate(
                degrees = angle.toFloat()
            ) {
                translate(
                    top = (size.height/2) - (sqrSize/2),
                    left = (size.width/2) - (sqrSize/2)
                ) {
                    drawRect(
                        Color.Green,
                        size = Size(sqrSize, sqrSize)
                    )
                }
            }
        }
        Text(text = "Angle: $angle")
    }
}