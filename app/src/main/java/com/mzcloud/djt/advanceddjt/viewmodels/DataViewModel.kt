package com.mzcloud.djt.advanceddjt.viewmodels

import androidx.databinding.ObservableField
import com.mzcloud.njt.module_core.brige.BaseViewModel

class DataViewModel(data: String) : BaseViewModel() {
    val data = ObservableField<String>(data)
}