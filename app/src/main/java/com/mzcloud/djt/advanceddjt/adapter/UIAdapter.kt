package com.mzcloud.djt.advanceddjt.adapter

import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.mzcloud.njt.module_core.utils.AESCrypt

@BindingAdapter("editPassword")
fun generatePassword(editText: EditText, password: String? = "") {
    val generatedPassword = AESCrypt.decrypt(password)
    editText.setText(generatedPassword)
}