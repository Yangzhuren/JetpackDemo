package com.mzcloud.djt.advanceddjt.ui.farmmain

import androidx.lifecycle.ViewModelProviders
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mzcloud.djt.advanceddjt.R
import com.mzcloud.djt.advanceddjt.viewmodels.FarmMainViewModel
import com.mzcloud.njt.module_core.ui.BaseFragment

class FarmMainFragment : BaseFragment() {

    companion object {
        fun newInstance() = FarmMainFragment()
    }

    private lateinit var viewModel: FarmMainViewModel

    override fun bindLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(R.layout.farm_main_fragment, container, false)
    }

    override fun initData() {
        viewModel = ViewModelProviders.of(this).get(FarmMainViewModel::class.java)
    }

}
