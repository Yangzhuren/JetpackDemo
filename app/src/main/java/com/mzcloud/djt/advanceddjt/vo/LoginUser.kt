package com.mzcloud.djt.advanceddjt.vo

import com.mzcloud.djt.advanceddjt.data.AppRole


data class LoginUser(
        val userId: Long,
        val userType: Int,
        val realName: String,
        val mobile: String,
        val appRole: List<AppRole>,
        val sessionId: String,
        val dicVer: Int,
        val deviceId: String
)