package com.tanucode.levelup.ui.space.spaces

import android.graphics.Matrix

// te ayudan a pasar Matrix ←→ FloatArray
fun Matrix.toValues(): FloatArray {
    val vals = FloatArray(9)
    this.getValues(vals)
    return vals
}
fun FloatArray.toMatrix(): Matrix {
    val m = Matrix()
    m.setValues(this)
    return m
}