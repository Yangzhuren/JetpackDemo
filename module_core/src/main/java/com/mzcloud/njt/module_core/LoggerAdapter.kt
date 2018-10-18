package com.mzcloud.njt.module_core

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy

class LoggerAdapter(format: FormatStrategy, private val isDebug: Boolean) : AndroidLogAdapter(format) {
    override fun isLoggable(priority: Int, tag: String?): Boolean {
        return isDebug
    }
}