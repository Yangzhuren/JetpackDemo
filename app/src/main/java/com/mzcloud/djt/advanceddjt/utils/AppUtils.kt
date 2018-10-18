package com.mzcloud.djt.advanceddjt.utils

import android.content.Context

object AppUtils {
    fun getVersionName(context: Context): String {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }
}