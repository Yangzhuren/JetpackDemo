package com.mzcloud.djt.advanceddjt.repository

import com.google.gson.JsonObject
import com.mzcloud.djt.advanceddjt.dao.AppRoleDao
import com.mzcloud.djt.advanceddjt.dao.LoginDao
import com.mzcloud.djt.advanceddjt.dao.UserDao
import com.mzcloud.djt.advanceddjt.injector.LoginInjector
import com.mzcloud.djt.advanceddjt.utils.runOnIoThread
import com.mzcloud.djt.advanceddjt.vo.LoginUser
import io.reactivex.Observable

// 登录的操作 包括本地数据库操作和与后端交互
class LoginRepository private constructor(private val appRoleDao: AppRoleDao, private val userDao: UserDao, private val loginDao: LoginDao) {

    fun getLastLoginUser() = userDao.getLastLoginUser()

    fun login(account: String, password: String): Observable<JsonObject> {
        return loginDao.login(account, password)
    }

    // 保存登录人信息
    fun saveUserInfo(loginUser: LoginUser, account: String, password: String) {
        runOnIoThread {
            userDao.insertUser(LoginInjector.provideUser(loginUser, account, password))
            appRoleDao.insertRoles(LoginInjector.provideAppRoles(loginUser))
        }
    }

    companion object {
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance(appRoleDao: AppRoleDao, userDao: UserDao, loginDao: LoginDao): LoginRepository =
                instance ?: synchronized(this) {
                    instance
                            ?: LoginRepository(appRoleDao, userDao, loginDao).also { instance = it }
                }
    }
}