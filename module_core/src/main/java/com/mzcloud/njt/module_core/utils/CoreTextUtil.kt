package com.mzcloud.njt.module_core.utils

import android.text.TextUtils

fun getNonNullText(text: String?): String {
    if (text == null || text.isEmpty()) return ""
    return text
}