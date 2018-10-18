package com.mzcloud.njt.module_core.utils

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.lang.Exception
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

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

    fun <T> toObj(jsonObject: JsonObject, type: Type): T =
            gson.fromJson(jsonObject, type)

    fun <T> toObj(json: String, type: Type): T =
            gson.fromJson(json, type)

    fun <T> toArray(jsonArray: JsonArray, type: Type): MutableList<T> {
        val objects = mutableListOf<T>()
        if (jsonArray.size() == 0) return objects
        for (jsonElement in jsonArray) {
            val objectT = toObj(jsonElement.asJsonObject, type) as T
            objects.add(objectT)
        }
        return objects
    }

    fun <T> toArrayT(jsonArray: JsonArray, type: Type):T?{
        val a = object :TypeToken<T>(){}.rawType
//        val list = mutableListOf<>()
        return null
    }

}