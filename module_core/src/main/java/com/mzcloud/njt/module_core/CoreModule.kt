package com.mzcloud.njt.module_core

import android.app.Application
import com.mzcloud.njt.module_core.http.HttpUtil
import com.mzcloud.njt.module_core.utils.FileUtil
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

object CoreModule {
    fun init(context: Application, baseUrl: String, isDebug: Boolean) {
        FileUtil.initDirs()
        HttpUtil.init(context, baseUrl)
        initLogger(isDebug)
    }

    private fun initLogger(isDebug: Boolean) {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .methodCount(2)
                .methodOffset(7)
                .build()
        val androidLogAdapter = LoggerAdapter(formatStrategy, isDebug)
        Logger.addLogAdapter(androidLogAdapter)
    }
}