package com.mzcloud.njt.module_core.http

import com.google.gson.JsonObject
import com.mzcloud.njt.module_core.utils.GsonUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class AbObserver : Observer<JsonObject> {
    lateinit var disposable: Disposable

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
    }

    override fun onNext(t: JsonObject) {
        val httpResponse = GsonUtil.toObj(t, HttpResponse::class.java)
        if (httpResponse.success && httpResponse.code === 200) {
            success(httpResponse.result)
        } else {
            error(httpResponse.message)
        }
    }

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    abstract fun success(result: JsonObject?)

    abstract fun error(msg: String?)

}