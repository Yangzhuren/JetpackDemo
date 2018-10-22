package com.mzcloud.njt.module_core.utils

import android.content.Context
import android.view.View
import android.widget.Toast

object ToastUtil {

    fun toastCustomView(context: Context, view: View, duration: Int) {
        val toast = Toast(context)
        toast.view = view
        toast.duration = duration
        toast.show()
    }
}