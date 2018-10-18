package com.mzcloud.djt.advanceddjt

import android.app.Application
import com.mzcloud.djt.advanceddjt.constants.PampasCalls
import com.mzcloud.njt.module_core.CoreModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

class MainApplication : Application() {
    companion object {
        const val DEBUG = true
    }

    // init some modules
    override fun onCreate() {
        super.onCreate()
        CoreModule.init(this,PampasCalls.BASE_URL,DEBUG)
    }


}