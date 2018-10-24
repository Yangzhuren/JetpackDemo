package com.mzcloud.djt.advanceddjt.repository

import androidx.lifecycle.LiveData
import com.mzcloud.djt.advanceddjt.dao.DConfigurationDao
import com.mzcloud.djt.advanceddjt.data.Configuration
import com.mzcloud.djt.advanceddjt.utils.runOnIoThread

class ConfigRepository private constructor(private val configurationDao: DConfigurationDao) {

    fun getCurrentVersionOpenedRecords(version: String?): LiveData<List<Configuration>> =
            configurationDao.getCurrentVersionOpenedRecords(version)


    fun createOpenRecord(configuration: Configuration) {
        runOnIoThread {
            configurationDao.insertCurrentOpenRecord(configuration)
        }
    }

    companion object {
        @Volatile
        private var instance: ConfigRepository? = null

        fun getInstance(configurationDao: DConfigurationDao): ConfigRepository =
                instance ?: synchronized(this) {
                    instance ?: ConfigRepository(configurationDao).also { instance = it }
                }
    }

    interface OnConfigGetSuccess {
        fun onConfigGetSuccess(openRecords: List<Configuration>)
    }
}