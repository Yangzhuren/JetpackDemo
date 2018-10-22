package com.mzcloud.djt.advanceddjt.utils

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

var toast: Toast? = null

internal fun Activity.toast(message: String) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    toast!!.show()
}

internal fun Activity.toast(@StringRes stringId: Int) {
    toast?.cancel()
    toast = Toast.makeText(this, stringId, Toast.LENGTH_SHORT)
    toast!!.show()
}