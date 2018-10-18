package com.mzcloud.djt.advanceddjt.ui.farmmain

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mzcloud.djt.advanceddjt.R

class FarmTrendFragment : Fragment() {

    companion object {
        fun newInstance() = FarmTrendFragment()
    }

    private lateinit var viewModel: FarmTrendViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.farm_trend_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FarmTrendViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
