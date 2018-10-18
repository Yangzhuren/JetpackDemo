package com.mzcloud.njt.module_core.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.os.Build
import android.provider.Settings
import java.security.MessageDigest
import kotlin.experimental.and

object AppUtil {
    private lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
    }

    fun getAppVersionCode(): Long? {
        return getPackageInfo()?.longVersionCode
    }

    fun getAppVersionName(): String? {
        return getPackageInfo()?.versionName
    }

    private fun getPackageInfo(): PackageInfo? {
        if (mContext == null) return null
        val packageManager = mContext.packageManager
        return packageManager.getPackageInfo(mContext.packageName, 0)
    }


    fun getDeviceId(): String {
        return Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID)
    }

    fun getDeviceName(): String {
        return Build.MODEL
    }

    fun getDeviceVersion():String{
        return Build.MODEL + " Android " + Build.VERSION.RELEASE + " " + Build.VERSION.SDK_INT
    }
}