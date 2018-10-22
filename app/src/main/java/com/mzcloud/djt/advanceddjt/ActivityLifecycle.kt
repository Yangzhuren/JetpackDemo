package com.mzcloud.djt.advanceddjt

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.umeng.analytics.MobclickAgent

class ActivityLifecycle : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity?) {
        MobclickAgent.onPause(activity)
    }

    override fun onActivityResumed(activity: Activity?) {
        MobclickAgent.onResume(activity)
    }

    override fun onActivityStarted(activity: Activity?) {
    }

    override fun onActivityDestroyed(activity: Activity?) {
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
    }

    override fun onActivityStopped(activity: Activity?) {
    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }
}