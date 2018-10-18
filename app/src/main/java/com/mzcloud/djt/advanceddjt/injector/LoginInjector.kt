package com.mzcloud.djt.advanceddjt.injector

import android.content.Context
import com.mzcloud.djt.advanceddjt.data.AppDataBase
import com.mzcloud.djt.advanceddjt.data.AppRole
import com.mzcloud.djt.advanceddjt.data.User
import com.mzcloud.djt.advanceddjt.model.LoginModel
import com.mzcloud.djt.advanceddjt.repository.LoginRepository
import com.mzcloud.djt.advanceddjt.viewmodels.LoginViewModelFactory
import com.mzcloud.djt.advanceddjt.vo.LoginUser

object LoginInjector {
    private fun getLoginRepository(context: Context) = LoginRepository.getInstance(AppDataBase.getInstance(context).appRoleDao(), AppDataBase.getInstance(context).userDao(), LoginModel())

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        val loginRepository = getLoginRepository(context)
        return LoginViewModelFactory(loginRepository)
    }

    fun provideUser(loginUser: LoginUser, account: String, password: String): User {
        val user = User(loginUser.userId, account, password)
        user.sessionId = loginUser.sessionId
        user.userType = loginUser.userType
        user.deviceId = loginUser.deviceId
        user.realName = loginUser.realName
        user.mobile = loginUser.mobile
        user.dicVer = loginUser.dicVer
        return user
    }

    fun provideAppRoles(loginUser: LoginUser):List<AppRole>{
        return loginUser.appRole.map {
            it.userId = loginUser.userId
            it
        }
    }
}