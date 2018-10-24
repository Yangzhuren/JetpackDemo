package com.mzcloud.djt.advanceddjt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mzcloud.djt.advanceddjt.data.Configuration
import com.mzcloud.djt.advanceddjt.databinding.StartActivityBinding
import com.mzcloud.djt.advanceddjt.injector.ConfigInjector
import com.mzcloud.djt.advanceddjt.repository.ConfigRepository
import com.mzcloud.djt.advanceddjt.viewmodels.ConfigListViewModel
import com.mzcloud.njt.module_core.utils.AppUtil

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<StartActivityBinding>(this, R.layout.start_activity)
        val version = AppUtil.getAppVersionName()
        val factory = ConfigInjector.provideConfigListViewModelFactory(this, version)
        val viewModel = ViewModelProviders.of(this, factory).get(ConfigListViewModel::class.java)
        // 数据库获取当前版本登录次数，若没有登录，则显示简介信息
        viewModel.openRecords.observe(this, Observer {
            onConfigGetSuccess(it)
        })
        viewModel.createOpenRecord(Configuration(AppUtil.getAppVersionName()))
    }

    private fun onConfigGetSuccess(openRecords: List<Configuration>?) {
        if (openRecords == null || openRecords.isEmpty()) {
            GuideActivity.actionStart(this)
        } else {
            // login
            LoginActivity.actionStart(this)
        }
    }
}
