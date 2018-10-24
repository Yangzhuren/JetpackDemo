package com.mzcloud.njt.module_core.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.umeng.analytics.MobclickAgent

abstract class BaseFragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        onPressed(v)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return bindLayout(inflater, container)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
        initListeners()
    }

    abstract fun bindLayout(inflater: LayoutInflater, container: ViewGroup?): View

    open fun initData(savedInstanceState: Bundle?) {}

    open fun initListeners() {}

    open fun onPressed(v: View?) {}


    override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart("${this.javaClass.`package`.name}.${this.javaClass.simpleName}")
    }

    override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd("${this.javaClass.`package`.name}.${this.javaClass.simpleName}")
    }

}