package com.mzcloud.njt.module_core.http

import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.google.gson.JsonObject
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken
import com.mzcloud.njt.module_core.utils.GsonUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class AbObserver : Observer<JsonObject> {

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        error(e.message)
    }

    override fun onNext(t: JsonObject) {
        val httpResponse = GsonUtil.toObj(t, HttpResponse::class.java)
        if (httpResponse.success && httpResponse.code === 200) {
            val result = httpResponse.result.toString()
            if (result == "null") {
                success(null)
            } else {
                success(result)
            }

        } else {
            error(httpResponse.message)
        }
    }

    abstract fun success(result: String?)

    abstract fun error(msg: String?)

}