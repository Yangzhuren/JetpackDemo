package com.mzcloud.djt.advanceddjt.ui.farmmain

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mzcloud.djt.advanceddjt.R
import com.mzcloud.djt.advanceddjt.viewmodels.SupervisionViewModel

class SupervisionFragment : Fragment() {

    companion object {
        fun newInstance() = SupervisionFragment()
    }

    private lateinit var viewModel: SupervisionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.supervision_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SupervisionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
