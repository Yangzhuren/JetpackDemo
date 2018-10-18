package com.mzcloud.njt.module_core.ui

import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {

    }

    abstract fun onPress(v: View?)

}