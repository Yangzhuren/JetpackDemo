package com.mzcloud.njt.module_core.utils

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.lang.Exception

object GsonUtil {
    private val gson = Gson()

    fun toJson(any: Any?): String {
        try {
            return gson.toJson(any)
        } catch (e: Exception) {
            return ""
            e.printStackTrace()
        }
    }

    fun <T> toObj(jsonObject: JsonObject, clazz: Class<T>): T =
            gson.fromJson(jsonObject, clazz)

}