package com.mzcloud.njt.module_core.brige

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    protected val disposables: MutableList<Disposable> = mutableListOf()

    override fun onCleared() {
        super.onCleared()
        if (!disposables.isEmpty()) {
            disposables.map {
                if (!it?.isDisposed) {
                    it.dispose()
                }
            }
        }
    }
}