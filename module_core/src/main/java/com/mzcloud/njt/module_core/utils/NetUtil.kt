package com.mzcloud.njt.module_core.utils

import android.content.Context
import android.net.ConnectivityManager

object NetUtil {
    fun isConnected(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo.isConnected) {
            return true
        }
        return false
    }
}