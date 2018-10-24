package com.mzcloud.djt.advanceddjt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputLayout
import com.mzcloud.djt.advanceddjt.constants.invalidateAccount
import com.mzcloud.djt.advanceddjt.constants.invalidatePassword
import com.mzcloud.djt.advanceddjt.constants.loginSuccess
import com.mzcloud.djt.advanceddjt.data.AppRole
import com.mzcloud.djt.advanceddjt.databinding.ActivityLoginBinding
import com.mzcloud.djt.advanceddjt.injector.LoginInjector
import com.mzcloud.djt.advanceddjt.utils.toast
import com.mzcloud.djt.advanceddjt.viewmodels.LoginViewModel
import com.mzcloud.njt.module_core.ui.CheckPermissionsActivity
import com.mzcloud.njt.module_core.utils.AESCrypt
import com.mzcloud.njt.module_core.utils.Dialogs

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
        loginViewModel.lastLoginUser.observe(this, Observer {
            binding.loginEtPassword.setText(AESCrypt.decrypt(it?.password))
        })
        loginViewModel.loginSuccess.observe(this, Observer {
            if (it) {
                // 登录成功
            }
        })
        loginViewModel.errorMessage.observe(this, Observer {
            toast(it)
        })
        loginViewModel.currentAppRoles.observe(this, Observer {
            if (it.isEmpty()) {
                toast(R.string.toast_login_info_miss)
            }
            if (it.size == 1) {
                toast(loginSuccess)
                FarmMainActivity.actionStart(this)
            } else if (it.size > 1) {
                showBottomSheet(it)
            }
        })
    }

    private fun showBottomSheet(roles: List<AppRole>) {
        val data = List(roles.size) {
            roles[it].roleName
        }

        Dialogs.createBottomSheetDialog(this, data, object : Dialogs.OnItemSelected {
            override fun onItemSelected(view: View, index: Int) {
                if (index != -1) {
                    toast(loginSuccess)
                    val selectedRole = roles[index]
                    MainApplication.loginRole = selectedRole
                    FarmMainActivity.actionStart(this@LoginActivity)
                }
            }
        }).show()
    }

    private fun validateAccount(account: String, tilAccount: TextInputLayout): Boolean {
        if (account.length != 11) {
            tilAccount.isErrorEnabled = true
            tilAccount.error = invalidateAccount
            return false
        }
        tilAccount.isErrorEnabled = false
        return true
    }

    private fun validatePassword(password: String, tilPassword: TextInputLayout): Boolean {
        if (password.length < 6) {
            tilPassword.isErrorEnabled = true
            tilPassword.error = invalidatePassword
            return false
        }
        tilPassword.isErrorEnabled = false
        return true
    }
}
