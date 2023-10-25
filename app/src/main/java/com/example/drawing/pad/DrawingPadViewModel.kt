package com.example.drawing.pad

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

data class Circle (
    var centroidSize: Float = 0f,
    var position: Offset = Offset(0f,0f),
    var color: Color = Color.Black
)

class DrawingUIState: ViewModel() {
    private var _circles = MutableStateFlow((mutableListOf<Circle>()))

    fun circles(): List<Circle> {
        return _circles.value
    }

    fun addCircle(circle: Circle) {
        _circles.value.add(circle)
    }
}