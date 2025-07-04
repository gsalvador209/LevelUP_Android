package com.tanucode.levelup.ui.space.spaces

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.atan2
import kotlin.math.sqrt

class DraggableStickerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    private var matrix = Matrix()
    private var savedMatrix = Matrix()

    private enum class Mode { NONE, DRAG, ZOOM, ROTATE }
    private var mode = Mode.NONE

    // para drag
    private var startX = 0f
    private var startY = 0f

    // para zoom/rotate
    private var oldDist = 1f
    private var oldAngle = 0f

    init {
        scaleType = ScaleType.MATRIX
        imageMatrix = matrix
        isClickable = true
        isFocusable = true
    }

    private val gestureDetector = GestureDetector(
        context,
        object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                (parent as? ViewGroup)?.removeView(this@DraggableStickerView)
                return true
            }
        })


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (gestureDetector.onTouchEvent(event)) return true
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                savedMatrix.set(matrix)
                startX = event.x
                startY = event.y
                mode = Mode.DRAG
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                oldDist = spacing(event)
                oldAngle = rotation(event)
                savedMatrix.set(matrix)
                mode = Mode.ZOOM
            }
            MotionEvent.ACTION_MOVE -> {
                when (mode) {
                    Mode.DRAG -> {
                        val dx = event.x - startX
                        val dy = event.y - startY
                        matrix.set(savedMatrix)
                        matrix.postTranslate(dx, dy)
                    }
                    Mode.ZOOM -> {
                        val newDist = spacing(event)
                        val scale = newDist / oldDist
                        val newAngle = rotation(event) - oldAngle
                        matrix.set(savedMatrix)
                        // pivote en el centro de los dos dedos
                        val px = (event.getX(0) + event.getX(1)) / 2
                        val py = (event.getY(0) + event.getY(1)) / 2
                        matrix.postScale(scale, scale, px, py)
                        matrix.postRotate(newAngle, px, py)
                    }
                    else -> { }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                mode = Mode.NONE
            }
        }
        imageMatrix = matrix
        return true
    }

    /** distancia entre los dos dedos */
    private fun spacing(event: MotionEvent): Float {
        val dx = event.getX(0) - event.getX(1)
        val dy = event.getY(0) - event.getY(1)
        return sqrt(dx * dx + dy * dy)
    }

    /** ángulo entre los dos dedos */
    private fun rotation(event: MotionEvent): Float {
        val dx = event.getX(0) - event.getX(1)
        val dy = event.getY(0) - event.getY(1)
        return Math.toDegrees(atan2(dy, dx).toDouble()).toFloat()
    }
}