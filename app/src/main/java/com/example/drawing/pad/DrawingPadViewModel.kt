package com.example.drawing.pad

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.moriafly.regret.Regret
import kotlinx.coroutines.flow.MutableStateFlow

var regret: Regret = Regret(
    onDo = { key: String, value: Any ->
        //How to UNDO a specific action!
        when(key) {
            "DRAWN" -> {
                Log.d("REGRET", "Undoing the CANVAS_DRAWING action")
            }
            "CHANGED_COLOR" -> {
                Log.d("REGRET", "Undoing the CANVAS_DRAWING action")
            }
            else -> {
                Log.d("REGRET","I cannot UNDO this Action!")
            }
        }
    },
    onCanDo = { canUndo: Boolean, canRedo: Boolean ->
        //How should the interface be updated!
    }
)


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

    fun undo() {
        _circles.value.removeLastOrNull()
    }
}