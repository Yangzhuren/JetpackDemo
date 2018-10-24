package com.mzcloud.djt.advanceddjt.viewmodels

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.mzcloud.djt.advanceddjt.constants.noUserInfo
import com.mzcloud.djt.advanceddjt.data.AppRole
import com.mzcloud.djt.advanceddjt.data.User
import com.mzcloud.djt.advanceddjt.repository.LoginRepository
import com.mzcloud.djt.advanceddjt.vo.LoginUser
import com.mzcloud.njt.module_core.brige.BaseViewModel
import com.mzcloud.njt.module_core.http.AbObserver
import com.mzcloud.njt.module_core.http.HttpUtil
import com.mzcloud.njt.module_core.utils.AESCrypt
import com.mzcloud.njt.module_core.utils.GsonUtil
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class LoginViewModel internal constructor(private val loginRepository: LoginRepository) : BaseViewModel() {

    var lastLoginUser: LiveData<User> = loginRepository.getLastLoginUser()

    var currentAppRoles: MutableLiveData<List<AppRole>> = MutableLiveData()

    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    val errorMessage: MutableLiveData<String> = MutableLiveData()

    val loading: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loginSuccess.value = false
        loading.value = false
    }

    fun login(account: String, password: String) {
        loading.value = true
        loginRepository.login(account, password)
                .subscribe(object : AbObserver() {
                    override fun onSubscribe(d: Disposable) {
                        disposables.add(d)
                    }

                    override fun onComplete() {
                        loading.value = false
                    }

                    override fun success(result: String?) {

                        if (result != null || result == "null") {

                            loginSuccess.value = true
                            val loginUser = GsonUtil.toObj<LoginUser>(result, LoginUser::class.java)
                            loginRepository.saveUserInfo(loginUser, account, password)
                            HttpUtil.setSessionId(loginUser.sessionId)
                            currentAppRoles.value = loginUser.appRole

                        } else {
                            errorMessage.value = noUserInfo
                        }
                    }

                    override fun error(msg: String?) {
                        loginSuccess.value = false
                        errorMessage.value = msg
                    }
                })
    }

}