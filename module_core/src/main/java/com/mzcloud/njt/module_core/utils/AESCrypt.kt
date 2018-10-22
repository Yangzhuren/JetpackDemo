package com.mzcloud.njt.module_core.utils

import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.SecretKeySpec

object AESCrypt {
    const val Algorithm = "AES";
    const val HEX = "nongjiantongwell";

    //加密函数，key为密钥
    fun encrypt(key: String, src: String): String {
        val rawKey = getRawKey(key.toByteArray());
        val result = encrypt(rawKey, src.toByteArray());
        return toHex(result);
    }

    //解密函数。key值必须和加密时的key一致
    fun decrypt(key: String, encrypted: String): String {
        val rawKey = getRawKey(key.toByteArray());
        val enc = toByte(encrypted);
        val result = decrypt(rawKey, enc);
        return String(result);
    }

    private fun appendHex(sb: StringBuffer, b: Byte) {
        sb.append(HEX[((b.toInt() shr 4) and 0x0f)]).append(HEX[(b.toInt() and 0x0f)]);
    }

    private fun getRawKey(seed: ByteArray): ByteArray {
        val kgen = KeyGenerator.getInstance(Algorithm);
        // SHA1PRNG 强随机种子算法, 要区别Android 4.2.2以上版本的调用方法
        var sr = SecureRandom()
//        if (android.os.Build.VERSION.SDK_INT >= 17) {
//            sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
//        } else {
//            sr = SecureRandom.getInstance("SHA1PRNG");
//        }
        sr.setSeed(seed);
        kgen.init(256, sr); // 256位或128位或192位
        val skey = kgen.generateKey();
        val raw = skey.encoded;
        return raw;
    }

    private fun encrypt(key: ByteArray, src: ByteArray): ByteArray {
        val skeySpec = SecretKeySpec(key, Algorithm);
        val cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        val encrypted = cipher.doFinal(src);
        return encrypted;
    }

    private fun decrypt(key: ByteArray, encrypted: ByteArray): ByteArray {
        val skeySpec = SecretKeySpec(key, Algorithm);
        val cipher = Cipher.getInstance(Algorithm);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        val decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }

    private fun toByte(hexString: String): ByteArray {
        val len = hexString.length / 2;
        val result = ByteArray(len)
        for (i in 0..len - 1) {
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).toByte();
        }
        return result;
    }

    private fun toHex(buf: ByteArray?): String {
        if (buf == null) {
            return "";
        }
        val result = StringBuffer(2 * buf.size);
        for (byte in buf) {
            appendHex(result, byte);
        }
        return result.toString();
    }
}