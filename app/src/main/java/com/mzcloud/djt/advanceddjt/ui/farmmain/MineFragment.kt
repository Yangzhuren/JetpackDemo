package com.mzcloud.djt.advanceddjt.ui.farmmain

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mzcloud.djt.advanceddjt.adapter.MineAdapter
import com.mzcloud.djt.advanceddjt.databinding.MineFragmentBinding
import com.mzcloud.djt.advanceddjt.injector.MineInjector
import com.mzcloud.djt.advanceddjt.viewmodels.MineViewModel

class MineFragment : Fragment() {

    companion object {
        fun newInstance() = MineFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = MineFragmentBinding.inflate(inflater, container, false)
        val adapter = MineAdapter(context)
        binding.dataList.adapter = adapter
        binding.dataList.layoutManager = LinearLayoutManager(context)
        binding.dataList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: MineAdapter, binding: MineFragmentBinding) {
        val factory = MineInjector.provideMineViewModelFactory(context!!)
        val viewModel = ViewModelProviders.of(this, factory).get(MineViewModel::class.java)
        viewModel.datas.observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) {
                adapter.submitList(it)
                binding.hasData = true
            } else {
                binding.hasData = false
            }
        })
    }

}
