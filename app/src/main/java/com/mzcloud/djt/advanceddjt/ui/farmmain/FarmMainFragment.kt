package com.mzcloud.djt.advanceddjt.ui.farmmain

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mzcloud.djt.advanceddjt.R

class FarmMainFragment : Fragment() {

    companion object {
        fun newInstance() = FarmMainFragment()
    }

    private lateinit var viewModel: FarmMainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.farm_main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FarmMainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
