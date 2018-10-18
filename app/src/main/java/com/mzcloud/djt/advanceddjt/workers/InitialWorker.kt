package com.mzcloud.djt.advanceddjt.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mzcloud.djt.advanceddjt.data.Configuration
import com.mzcloud.djt.advanceddjt.injector.ConfigInjector
import com.mzcloud.djt.advanceddjt.utils.AppUtils

class InitialWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        // do something after db init
        return Worker.Result.SUCCESS
    }
}