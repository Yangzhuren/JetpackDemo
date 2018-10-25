package com.mzcloud.djt.advanceddjt.adapter

import android.view.View
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.mzcloud.njt.module_core.utils.AESCrypt

@BindingAdapter("editPassword")
fun generatePassword(editText: EditText, password: String? = "") {
    val generatedPassword = AESCrypt.decrypt(password)
    editText.setText(generatedPassword)
}

@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}