package com.mzcloud.djt.advanceddjt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputLayout
import com.mzcloud.djt.advanceddjt.databinding.ActivityLoginBinding
import com.mzcloud.djt.advanceddjt.injector.LoginInjector
import com.mzcloud.djt.advanceddjt.viewmodels.LoginViewModel
import com.mzcloud.njt.module_core.ui.CheckPermissionsActivity

class LoginActivity : CheckPermissionsActivity() {

    companion object {
        fun actionStart(activity: Activity) {
            activity.startActivity(Intent(activity, LoginActivity::class.java))
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LoginInjector.provideLoginViewModelFactory(this)
        val loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(
                this, R.layout.activity_login
        ).apply {
            viewModel = loginViewModel
            setLifecycleOwner(this@LoginActivity)
            loginBtnLogin.setOnClickListener {
                val account = loginEtAccount.text.toString()
                val password = loginEtPassword.text.toString()
                if (validateAccount(account, tilAccount) && validatePassword(password, tilPassword)) {
                    loginViewModel.login(account, password)
                }
            }
        }
        loginViewModel.loginSuccess.observe(this, Observer {
            if (it) {
                // 登录成功
                FarmMainActivity.actionStart(this)
            }
        })
    }

    private fun validateAccount(account: String, tilAccount: TextInputLayout): Boolean {
        if (account.length != 11) {
            tilAccount.isErrorEnabled = true
            tilAccount.error = "请输入正确的11位账号"
            return false
        }
        tilAccount.isErrorEnabled = false
        return true
    }

    private fun validatePassword(password: String, tilPassword: TextInputLayout): Boolean {
        if (password.length < 6) {
            tilPassword.isErrorEnabled = true
            tilPassword.error = "密码字数过少"
            return false
        }
        tilPassword.isErrorEnabled = false
        return true
    }
}
