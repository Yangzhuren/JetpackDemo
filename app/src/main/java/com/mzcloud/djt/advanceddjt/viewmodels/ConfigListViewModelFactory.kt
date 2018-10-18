package com.mzcloud.djt.advanceddjt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mzcloud.djt.advanceddjt.repository.ConfigRepository

class ConfigListViewModelFactory(private val configRepository: ConfigRepository, private val version: String?) : ViewModelProvider.NewInstanceFactory() {
    @SuppressWarnings("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ConfigListViewModel(configRepository, version) as T
    }
}