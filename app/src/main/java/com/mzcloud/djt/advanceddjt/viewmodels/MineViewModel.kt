package com.mzcloud.djt.advanceddjt.viewmodels

import androidx.lifecycle.LiveData
import com.mzcloud.djt.advanceddjt.repository.UserRepository
import com.mzcloud.njt.module_core.brige.BaseViewModel

class MineViewModel(private val userRepository: UserRepository) : BaseViewModel() {
    var datas:LiveData<List<String>> = userRepository.getUserNames()
}
