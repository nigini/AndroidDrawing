package com.example.drawing

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.calculateCentroidSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
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

data class Circle (
    var centroidSize: Float = 0f,
    var position: Offset = Offset(0f,0f),
    var color: Color = Color.Blue
)

@Composable
fun DrawOnClick() {
    var circles by remember { mutableStateOf(listOf<Circle>()) }
    //TODO: Add a color picker in the interface. Ex: https://www.section.io/engineering-education/creating-a-scratch-pad-with-jetpack-compose/
    val color1 = MaterialTheme.colorScheme.onPrimary
    var tempPosition by remember { mutableStateOf(Offset.Zero) }
    var tempSize by remember { mutableStateOf(0f) }
    Canvas(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .pointerInput(Unit) {
                awaitEachGesture {
                    awaitFirstDown().also {
                        tempPosition = it.position
                    }
                    do {
                        val event = awaitPointerEvent()
                        tempSize++
                    } while (event.changes.any { it.pressed })
                    circles = circles + Circle(tempSize, tempPosition, color1)
                    tempSize = 0f
                }
            }
            .drawBehind {
                drawCircle(color1, radius = tempSize, center = tempPosition)
            }
    ) {
        circles.forEach {
            Log.d("DRAWING", "Drawing circle ${it}")
            drawCircle(
                color = it.color,
                center = it.position,
                radius = it.centroidSize)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DrawingTheme {
        DrawOnClick()
    }
}