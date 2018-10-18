package com.mzcloud.njt.module_core.http

import com.mzcloud.njt.module_core.utils.GsonUtil
import com.orhanobut.logger.Logger
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val method = request.method()
        var url = request.url().toString()
        if ("POST" == method) {
            val body = request.body()
            val sb = StringBuilder()
            if (body is FormBody) {
                for (i in 0..body.size() - 1) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",")
                }
                if (sb.length > 0 && sb.toString().endsWith(",")) {
                    sb.delete(sb.length - 1, sb.length)
                }
                url += sb.toString()
            }
        }

        Logger.d("Sending request :method:%s%nurl:%s", method, url)

        val response = chain.proceed(request)

        val endTime = System.currentTimeMillis()
        Logger.d("Received response for %s in %sms%n%s", url, endTime - startTime, response.peekBody(1024 * 1024).string())
        return response
    }
}