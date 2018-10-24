package com.mzcloud.njt.module_core.http

import android.content.Context
import com.mzcloud.njt.module_core.utils.NetUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetUtil.isConnected(context)) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
        }
        val originalResponse = chain.proceed(request)
        if (NetUtil.isConnected(context)) {
            val cacheControl = request.cacheControl().toString()
            return originalResponse.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build()
        } else {
            return originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-age=${60 * 60}")
                    .removeHeader("Pragma")
                    .build()
        }
    }
}