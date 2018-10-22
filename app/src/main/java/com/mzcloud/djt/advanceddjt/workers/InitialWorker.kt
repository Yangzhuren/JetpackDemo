package com.mzcloud.djt.advanceddjt.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class InitialWorker(context: Context, workerParameters: WorkerParameters) : Worker(context, workerParameters) {

    override fun doWork(): Result {
        // do something after db init
        return Result.SUCCESS
    }
}