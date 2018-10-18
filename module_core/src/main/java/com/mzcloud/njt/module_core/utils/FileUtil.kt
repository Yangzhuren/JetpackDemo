package com.mzcloud.njt.module_core.utils

import android.os.Environment
import com.mzcloud.njt.module_core.BuildConfig
import java.io.File

object FileUtil {
    val SD_PATH = Environment.getExternalStorageDirectory().absolutePath
    val PATH_APP = SD_PATH + File.separator + BuildConfig.APPLICATION_ID
    val PATH_APP_CRASH = PATH_APP + File.separator + "crash"
    val PATH_APP_IMG = PATH_APP + File.separator + "image"
    val PATH_APP_VIDEO = PATH_APP + File.separator + "video"
    val PATH_APP_UPDATE = PATH_APP + File.separator + "update"

    fun hasSDCard(): Boolean {
        val status = Environment.getExternalStorageState()
        return Environment.MEDIA_MOUNTED.equals(status)
    }

    fun initDirs() {
        if (hasSDCard()) {
            makeDirs(PATH_APP_CRASH)
            makeDirs(PATH_APP_IMG)
            makeDirs(PATH_APP_IMG)
            makeDirs(PATH_APP_VIDEO)
            makeDirs(PATH_APP_UPDATE)
        }
    }

    private fun makeDirs(path: String) {
        val file = File(path)
        if (!file.exists()) {
            file.mkdirs()
        }
    }
}