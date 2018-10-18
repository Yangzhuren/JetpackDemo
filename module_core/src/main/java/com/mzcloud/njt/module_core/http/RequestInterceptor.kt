package com.mzcloud.njt.module_core.http

import com.mzcloud.njt.module_core.utils.GsonUtil
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val calendar = Calendar.getInstance()
        val startTime = calendar.timeInMillis
        Logger.d("Sending request %s on %s%n%s", request.url(), chain.connection(), GsonUtil.toJson(request.body()))

        val response = chain.proceed(request)

        val endTime = calendar.timeInMillis
        Logger.d("Received response for %s in %sms%n%s", response.request().url(), endTime - startTime, GsonUtil.toJson(request.body()))
        return response
    }
}