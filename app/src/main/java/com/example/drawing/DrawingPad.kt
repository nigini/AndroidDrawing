package com.example.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.abs
import kotlin.math.sqrt

data class Circle (
    var centroidSize: Float = 0f,
    var position: Offset = Offset(0f,0f),
    var color: Color = Color.Blue
)

@Composable
fun DrawOnClick() {
    //Idea from: https://stackoverflow.com/questions/64571945/
    var circles by remember { mutableStateOf(listOf<Circle>()) }
    //TODO: Add a color picker in the interface. Ex: https://www.section.io/engineering-education/creating-a-scratch-pad-with-jetpack-compose/
    val color1 = MaterialTheme.colorScheme.onPrimary
    var initialPosition by remember { mutableStateOf(Offset.Zero) }
    var tempSize by remember { mutableStateOf(0f) }
    Canvas(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .pointerInput(Unit) {
                awaitEachGesture {
//                    awaitFirstDown().also {
//                        initialPosition = it.position
//                    }
                    var pointReleased = false
                    do {
                        val event = awaitPointerEvent()
                        //Like this structure better: https://stackoverflow.com/questions/64571945/
                        event.changes.forEach {
                            when(event.type) {
                                PointerEventType.Press -> {
                                    initialPosition = it.position
                                }
                                PointerEventType.Move -> {
                                    tempSize = sqrt(abs( it.position.getDistanceSquared()-initialPosition.getDistanceSquared()))
                                }
                                PointerEventType.Release -> {
                                    pointReleased = true
                                }
                            }
                        }
                    } while (!pointReleased)//(event.changes.any { it.pressed })
                    circles = circles + Circle(tempSize, initialPosition, color1)
                    tempSize = 0f
                }
            }
            .drawBehind {
                drawCircle(color1, radius = tempSize, center = initialPosition)
            }
    ) {
        circles.forEach {
            drawCircle(
                color = it.color,
                center = it.position,
                radius = it.centroidSize)
        }
    }
}