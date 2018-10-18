package com.mzcloud.djt.advanceddjt.injector

import android.content.Context
import com.mzcloud.djt.advanceddjt.data.AppDataBase
import com.mzcloud.djt.advanceddjt.model.LoginModel
import com.mzcloud.djt.advanceddjt.repository.LoginRepository
import com.mzcloud.djt.advanceddjt.viewmodels.LoginViewModelFactory

object LoginInjector {
    private fun getLoginRepository(context: Context) = LoginRepository.getInstance(AppDataBase.getInstance(context).appRoleDao(), AppDataBase.getInstance(context).userDao(),LoginModel())

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        val loginRepository = getLoginRepository(context)
        return LoginViewModelFactory(loginRepository)
    }
}