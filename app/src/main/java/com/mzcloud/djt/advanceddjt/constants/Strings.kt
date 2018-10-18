package com.mzcloud.djt.advanceddjt.constants

import android.content.Context
import com.mzcloud.djt.advanceddjt.MainApplication
import com.mzcloud.djt.advanceddjt.R

object Strings {
    val context: Context = MainApplication.getContext()

    fun noUserInfo() = context.resources.getString(R.string.toast_no_user_info)
}