package com.mzcloud.njt.module_core.http

import android.accounts.NetworkErrorException
import android.content.Context
import android.os.Environment
import android.text.TextUtils
import com.google.gson.JsonObject
import com.mzcloud.njt.module_core.CoreModule
import com.mzcloud.njt.module_core.http.HttpConfig.APP_KEY_KEY
import com.mzcloud.njt.module_core.http.HttpConfig.APP_KEY_VALUE
import com.mzcloud.njt.module_core.http.HttpConfig.CONNECT_TIME_OUT
import com.mzcloud.njt.module_core.http.HttpConfig.SESSION_ID_KEY
import com.mzcloud.njt.module_core.utils.NetUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.RuntimeException
import java.util.*
import java.util.concurrent.TimeUnit

object HttpUtil {
    private lateinit var services: Services
    private lateinit var mContext: Context
    private var mSessionId: String = ""
    private const val cacheSize: Long = 1024 * 1024 * 20
    private val cacheDir: String = "${Environment.getExternalStorageDirectory()}${File.separator}okhttpcaches"

    internal fun init(context: Context, baseUrl: String, isDebug: Boolean) {
        mContext = context
        val httpClientBuilder = OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        httpClientBuilder.writeTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        httpClientBuilder.readTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
        if (isDebug)
            httpClientBuilder.addInterceptor(RequestInterceptor())
        val cache = Cache(File(cacheDir), cacheSize)
        httpClientBuilder.cache(cache)
        httpClientBuilder.addInterceptor(CacheInterceptor(mContext))
        httpClientBuilder.addNetworkInterceptor(CacheInterceptor(mContext))

        val okHttpClient = httpClientBuilder.build()
        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        services = retrofit.create(Services::class.java)
    }

    fun setSessionId(sessionId: String) {
        mSessionId = sessionId
    }

    fun get(params: Map<String, String>): Observable<JsonObject>? {
        return get(null, params)
    }

    fun get(cacheKey: String?, params: Map<String, String>): Observable<JsonObject>? {
        val modifiedParams = doBeforeRequest(params)
        if (!NetUtil.isConnected(mContext)) return ifNoNet()
        if (cacheKey != null) {
            // do cache
            return null
        } else {
            return services.get(modifiedParams)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun post(params: Map<String, String>): Observable<JsonObject> {
        if (!NetUtil.isConnected(mContext)) return ifNoNet()
        val modifiedParams = doBeforeRequest(params)
        return services.post(modifiedParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUrl(url: String): Observable<JsonObject> {
        if (!NetUtil.isConnected(mContext)) return ifNoNet()
        return services.getUrl(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * 无网络返回值
     */
    private fun ifNoNet(): Observable<JsonObject> {
        return Observable.error(NetworkErrorException("网络无连接，请连接网络"))
    }

    private fun doBeforeRequest(params: Map<String, String>): Map<String, String> {
        val paramsModified = params.toMutableMap()
        paramsModified.put(APP_KEY_KEY, APP_KEY_VALUE)
        if (!TextUtils.isEmpty(mSessionId))
            paramsModified.put(SESSION_ID_KEY, mSessionId)
        return paramsModified
    }

}