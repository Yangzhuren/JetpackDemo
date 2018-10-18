package com.mzcloud.njt.module_core.http

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.*

interface Services {
    @GET("api/gateway/")
    fun get(@QueryMap params: Map<String, String>): Observable<JsonObject>

    @FormUrlEncoded
    @POST("api/gateway/")
    fun post(@FieldMap params: Map<String, String>): Observable<JsonObject>

    @GET
    fun getUrl(@Url url: String): Observable<JsonObject>
}