package com.mzcloud.njt.module_core.http

import com.google.gson.JsonObject

class HttpResponse {
    val success: Boolean = false
    val code: Int = 0
    val result: JsonObject? = null
    val message: String? = null
}