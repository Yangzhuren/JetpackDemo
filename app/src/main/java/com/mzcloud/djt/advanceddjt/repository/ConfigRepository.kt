package com.mzcloud.djt.advanceddjt.repository

import com.mzcloud.djt.advanceddjt.dao.ConfigurationDao
import com.mzcloud.djt.advanceddjt.data.Configuration
import com.mzcloud.djt.advanceddjt.utils.runOnIoThread

class ConfigRepository private constructor(private val configurationDao: ConfigurationDao) {

    fun getCurrentVersionOpenedRecords(version: String,onConfigGetSuccess: OnConfigGetSuccess) {
        runOnIoThread {
            onConfigGetSuccess.onConfigGetSuccess(configurationDao.getCurrentVersionOpenedRecords(version))
        }
    }

    fun createOpenRecord(configuration: Configuration) {
        runOnIoThread {
            configurationDao.insertCurrentOpenRecord(configuration)
        }
    }

    companion object {
        @Volatile
        private var instance: ConfigRepository? = null

        fun getInstance(configurationDao: ConfigurationDao): ConfigRepository =
                instance ?: synchronized(this) {
                    instance ?: ConfigRepository(configurationDao).also { instance = it }
                }
    }

    interface OnConfigGetSuccess {
        fun onConfigGetSuccess(openRecords: List<Configuration>)
    }
}