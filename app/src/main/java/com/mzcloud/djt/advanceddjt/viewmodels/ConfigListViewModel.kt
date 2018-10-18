package com.mzcloud.djt.advanceddjt.viewmodels

import androidx.lifecycle.ViewModel
import com.mzcloud.djt.advanceddjt.data.Configuration
import com.mzcloud.djt.advanceddjt.repository.ConfigRepository

class ConfigListViewModel internal constructor(private val configRepository: ConfigRepository, private val version: String?) : ViewModel() {

    fun getOpenRecords(onConfigurationGetSuccess: ConfigRepository.OnConfigGetSuccess){
        configRepository.getCurrentVersionOpenedRecords(version,onConfigurationGetSuccess)
    }

    fun createOpenRecord(configuration: Configuration) {
        configRepository.createOpenRecord(configuration)
    }
}