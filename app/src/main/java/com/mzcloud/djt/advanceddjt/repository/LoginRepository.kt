package com.mzcloud.djt.advanceddjt.repository

import androidx.lifecycle.LiveData
import com.google.gson.JsonObject
import com.mzcloud.djt.advanceddjt.MainApplication
import com.mzcloud.djt.advanceddjt.dao.DAppRoleDao
import com.mzcloud.djt.advanceddjt.dao.MLoginDao
import com.mzcloud.djt.advanceddjt.dao.DUserDao
import com.mzcloud.djt.advanceddjt.data.User
import com.mzcloud.djt.advanceddjt.injector.LoginInjector
import com.mzcloud.djt.advanceddjt.utils.runOnIoThread
import com.mzcloud.djt.advanceddjt.vo.LoginUser
import com.mzcloud.njt.module_core.utils.AESCrypt
import io.reactivex.Observable

// 登录的操作 包括本地数据库操作和与后端交互
class LoginRepository private constructor(private val appRoleDao: DAppRoleDao, private val userDao: DUserDao, private val loginDao: MLoginDao) {

    fun getLastLoginUser(): LiveData<User> = userDao.getLastLoginUser()


    fun login(account: String, password: String): Observable<JsonObject> {
        return loginDao.login(account, password)
    }

    // 保存登录人信息
    fun saveUserInfo(loginUser: LoginUser, account: String, password: String) {
        runOnIoThread {
            val user = LoginInjector.provideUser(loginUser, account, password)
            user.password = AESCrypt.encrypt(user.password)
            userDao.insertUser(user)
            appRoleDao.insertRoles(LoginInjector.provideAppRoles(loginUser))
            MainApplication.loginUser = loginUser
        }
    }

    companion object {
        @Volatile
        private var instance: LoginRepository? = null

        fun getInstance(appRoleDao: DAppRoleDao, userDao: DUserDao, loginDao: MLoginDao): LoginRepository =
                instance ?: synchronized(this) {
                    instance
                            ?: LoginRepository(appRoleDao, userDao, loginDao).also { instance = it }
                }
    }
}