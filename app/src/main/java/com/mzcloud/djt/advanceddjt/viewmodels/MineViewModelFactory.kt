package com.mzcloud.djt.advanceddjt.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mzcloud.djt.advanceddjt.repository.UserRepository

class MineViewModelFactory(val userRepository: UserRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MineViewModel(userRepository) as T
    }
}