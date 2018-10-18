package com.mzcloud.njt.module_core.utils

import android.content.Context
import android.view.View
import android.widget.Toast

object ToastUtil {
    fun toastShort(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun toastLong(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }

    fun toastCustomView(context: Context, view: View, duration: Int) {
        val toast = Toast(context)
        toast.view = view
        toast.duration = duration
        toast.show()
    }
}