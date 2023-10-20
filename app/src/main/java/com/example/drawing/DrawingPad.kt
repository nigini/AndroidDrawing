package com.example.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import kotlin.math.abs
import kotlin.math.sqrt

data class Circle (
    var centroidSize: Float = 0f,
    var position: Offset = Offset(0f,0f),
    var color: Color = Color.Black
)

@Composable
@Preview
fun DrawOnClick() {
    var drawingColor by remember {mutableStateOf(Color.Black)}
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        ColorPicker1(){
            drawingColor = it
        }
        BubbleCanvas(drawingColor = drawingColor)
    }
}

@Composable
fun BubbleCanvas(drawingColor: Color) {
    //Idea from: https://stackoverflow.com/questions/64571945/
    var circles by remember { mutableStateOf(listOf<Circle>()) }
    var initialPosition by remember { mutableStateOf( Offset(0f,0f) ) }
    var tempSize by remember { mutableStateOf(0f) }
    var tempColor by remember { mutableStateOf(drawingColor) }

    Canvas(
        Modifier
            .fillMaxSize()
            .clipToBounds()
            .background(MaterialTheme.colorScheme.primary)
            .pointerInput(Unit) {
                awaitEachGesture {
                    var pointReleased = false
                    do {
                        val event = awaitPointerEvent()

                        //Like this structure better: https://stackoverflow.com/questions/64571945/
                        event.changes.forEach {
                            when (event.type) {
                                PointerEventType.Press -> {
                                    initialPosition = it.position
                                }

                                PointerEventType.Move -> {
                                    tempSize =
                                        sqrt(abs(it.position.getDistanceSquared() - initialPosition.getDistanceSquared()))
                                }

                                PointerEventType.Release -> {
                                    pointReleased = true
                                    circles += Circle(tempSize, initialPosition, tempColor)
                                    tempSize = 0f
                                }
                            }
                        }
                    } while (!pointReleased)
                }
            }
            .drawBehind {
                drawCircle(drawingColor, radius = tempSize, center = initialPosition)
            }
    ) {
        tempColor = drawingColor
        circles.forEach {
            drawCircle(
                color = it.color,
                center = it.position,
                radius = it.centroidSize
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker1(defaultColor:Color = Color.Black, onColorPick: (Color) -> Unit){
    var colorName by remember { mutableStateOf(defaultColor.toHexCodeWithAlpha()) }
    var currentColor by remember { mutableStateOf(defaultColor) }
    Row (
        modifier = Modifier
            .height(50.dp)
    ){
        TextField(
            value = colorName,
            onValueChange = {
                colorName = it
                currentColor = try {
                    Color(it.toColorInt())
                } catch (iaex: IllegalArgumentException) {
                    defaultColor
                }
                onColorPick(currentColor)
            }
        )
        Spacer(modifier = Modifier
            .background(currentColor)
            .weight(1f)
            .fillMaxHeight()
        )
    }
}

//FROM: https://stackoverflow.com/a/73458170/539223
fun Color.toHexCodeWithAlpha(): String {
    val alpha = this.alpha*255
    val red = this.red * 255
    val green = this.green * 255
    val blue = this.blue * 255
    return String.format("#%02x%02x%02x%02x", alpha.toInt(),red.toInt(), green.toInt(), blue.toInt())
}