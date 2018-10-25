package com.mzcloud.djt.advanceddjt.injector

import android.content.Context
import com.mzcloud.djt.advanceddjt.data.AppDataBase
import com.mzcloud.djt.advanceddjt.repository.UserRepository
import com.mzcloud.djt.advanceddjt.viewmodels.MineViewModelFactory

object MineInjector {
    private fun getUserRepository(context: Context): UserRepository = UserRepository.getInstance(AppDataBase.getInstance(context).userDao())

    fun provideMineViewModelFactory(context: Context): MineViewModelFactory =
            MineViewModelFactory(getUserRepository(context))

}