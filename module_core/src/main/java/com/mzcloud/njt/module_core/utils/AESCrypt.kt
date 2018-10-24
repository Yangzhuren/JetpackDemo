package com.mzcloud.njt.module_core.utils

import android.text.TextUtils
import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object AESCrypt {
    private const val password: String = "nongjiantongwell"

    fun encrypt(input: String): String {
        val cipher = Cipher.getInstance("AES")
        val keySpec = SecretKeySpec(password.toByteArray(), "AES")
        cipher.init(Cipher.ENCRYPT_MODE, keySpec)
        val encrypt = cipher.doFinal(input.toByteArray())
        return Base64.encodeToString(encrypt, Base64.DEFAULT)
    }

    fun decrypt(input: String?): String {
        if (TextUtils.isEmpty(input)) return ""
        val cipher = Cipher.getInstance("AES")
        val keySpec = SecretKeySpec(password.toByteArray(), "AES")
        cipher.init(Cipher.DECRYPT_MODE, keySpec)
        val decrypt = cipher.doFinal(Base64.decode(input?.trim()?.toByteArray(), Base64.DEFAULT))
        return String(decrypt)
    }
}