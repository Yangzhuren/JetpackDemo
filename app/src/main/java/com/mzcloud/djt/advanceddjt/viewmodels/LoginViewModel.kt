package com.mzcloud.djt.advanceddjt.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.mzcloud.djt.advanceddjt.data.User
import com.mzcloud.djt.advanceddjt.repository.LoginRepository
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class LoginViewModel internal constructor(private val loginRepository: LoginRepository) : ViewModel() {
    var disposable: Disposable? = null;

    val lastLoginUser: LiveData<User> = loginRepository.getLastLoginUser()

    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    init {
        loginSuccess.value = false
    }

    fun login(account: String, password: String) {
        val observable = LoginObserver()
        loginRepository.login(account, password)
                .subscribe(observable)
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }

    inner class LoginObserver : Observer<JsonObject> {
        override fun onComplete() {
            Logger.d("login complete")
        }

        override fun onError(e: Throwable) {
            Logger.e(e, "")
        }

        override fun onNext(t: JsonObject) {
            loginSuccess.value = true
            Logger.d(t)
        }

        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

    }
}