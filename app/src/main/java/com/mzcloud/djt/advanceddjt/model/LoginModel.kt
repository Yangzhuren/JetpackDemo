package com.mzcloud.djt.advanceddjt.model

import com.google.gson.JsonObject
import com.mzcloud.djt.advanceddjt.constants.API_LOGIN
import com.mzcloud.djt.advanceddjt.dao.MLoginDao
import com.mzcloud.njt.module_core.http.HttpUtil
import com.mzcloud.njt.module_core.utils.AppUtil
import io.reactivex.Observable

class LoginModel : MLoginDao {
    override fun login(name: String, password: String): Observable<JsonObject> {
        val params = mutableMapOf(
                "userName" to name,
                "password" to password,
                "pampasCall" to API_LOGIN,
                "appVer" to (AppUtil.getAppVersionName() ?: ""),
                "deviceId" to AppUtil.getDeviceId(),
                "deviceName" to AppUtil.getDeviceName(),
                "deviceVer" to AppUtil.getDeviceVersion()
        )
        return HttpUtil.post(params)
    }

}