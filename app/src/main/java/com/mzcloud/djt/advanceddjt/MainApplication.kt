package com.mzcloud.djt.advanceddjt

import android.app.Application
import com.mzcloud.djt.advanceddjt.constants.PampasCalls
import com.mzcloud.djt.advanceddjt.data.AppRole
import com.mzcloud.djt.advanceddjt.vo.LoginUser
import com.mzcloud.njt.module_core.CoreModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure

class MainApplication : Application() {
    companion object {
        lateinit var instance: MainApplication
        lateinit var loginUser: LoginUser
        lateinit var loginRole: AppRole

        fun getContext(): Application {
            return instance
        }
    }


    // init some modules
    override fun onCreate() {
        super.onCreate()
        CoreModule.init(this, PampasCalls.BASE_URL, BuildConfig.DEBUG)
        instance = this
        initUmeng()
        initLifecycle()
    }

    private fun initUmeng() {
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null)
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL)
    }

    // 与activity生命周期相关的封装
    private fun initLifecycle() {
        registerActivityLifecycleCallbacks(ActivityLifecycle())
    }
}