package com.mzcloud.djt.advanceddjt.model

import com.google.gson.JsonObject
import com.mzcloud.djt.advanceddjt.constants.PampasCalls.API_LOGIN
import com.mzcloud.djt.advanceddjt.dao.LoginDao
import com.mzcloud.njt.module_core.http.HttpUtil
import io.reactivex.Observable

class LoginModel : LoginDao {
    override fun login(name: String, password: String): Observable<JsonObject> {
        val params = mutableMapOf<String, String>("userName" to name, "password" to password, "pampasCall" to API_LOGIN)
        return HttpUtil.post(params)
    }

}