package com.mzcloud.djt.advanceddjt.dao

import com.google.gson.JsonObject
import io.reactivex.Observable

interface LoginDao {
    fun login(name: String, password: String): Observable<JsonObject>
}