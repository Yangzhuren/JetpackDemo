package com.mzcloud.djt.advanceddjt.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mzcloud.djt.advanceddjt.data.Configuration
import com.mzcloud.djt.advanceddjt.repository.ConfigRepository
import com.mzcloud.djt.advanceddjt.utils.AppUtils

class ConfigListViewModel internal constructor(private val configRepository: ConfigRepository, private val version: String) : ViewModel() {

    fun getOpenRecords(onConfigurationGetSuccess: ConfigRepository.OnConfigGetSuccess){
        configRepository.getCurrentVersionOpenedRecords(version,onConfigurationGetSuccess)
    }

    fun createOpenRecord(configuration: Configuration) {
        configRepository.createOpenRecord(configuration)
    }
}