package com.mzcloud.djt.advanceddjt.repository

import com.google.gson.JsonObject
import com.mzcloud.djt.advanceddjt.dao.AppRoleDao
import com.mzcloud.djt.advanceddjt.dao.LoginDao
import com.mzcloud.djt.advanceddjt.dao.UserDao
import com.mzcloud.djt.advanceddjt.utils.runOnIoThread
import io.reactivex.Observable

class LoginRepository private constructor(private val appRoleDao: AppRoleDao, private val userDao: UserDao, private val loginDao: LoginDao) {

    fun getLastLoginUser() = userDao.getLastLoginUser()

    fun login(account: String, password: String):Observable<JsonObject> {
        return loginDao.login(account, password)
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