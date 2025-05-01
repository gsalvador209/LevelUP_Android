package com.tanucode.levelup.util

import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackbar(view:View, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(view, message, duration)
        //.setTextColor(getColor(R.color.white))
        //.setBackgroundTint(getColor(R.color.add))
        .show()

}