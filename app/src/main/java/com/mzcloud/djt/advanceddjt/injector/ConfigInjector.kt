package com.mzcloud.djt.advanceddjt.injector

import android.content.Context
import com.mzcloud.djt.advanceddjt.data.AppDataBase
import com.mzcloud.djt.advanceddjt.repository.ConfigRepository
import com.mzcloud.djt.advanceddjt.viewmodels.ConfigListViewModelFactory

object ConfigInjector {
    private fun getConfigRepository(context: Context) = ConfigRepository.getInstance(AppDataBase.getInstance(context).configurationDao())

    fun provideConfigListViewModelFactory(context: Context, version: String?): ConfigListViewModelFactory {
        val repository = getConfigRepository(context)
        return ConfigListViewModelFactory(repository, version)
    }
}