package com.mzcloud.djt.advanceddjt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mzcloud.djt.advanceddjt.R
import com.mzcloud.djt.advanceddjt.databinding.ItemMineListBinding
import com.mzcloud.djt.advanceddjt.viewmodels.DataViewModel

class MineAdapter(private val context: Context?) : ListAdapter<String, MineAdapter.ViewHolder>(MineDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let {
            with(holder) {
                itemView.tag = it
                bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_mine_list, parent, false))
    }


    class ViewHolder(private val itemMineListBinding: ItemMineListBinding) : RecyclerView.ViewHolder(itemMineListBinding.root) {
        fun bind(string: String) {
            with(itemMineListBinding) {
                viewModel = DataViewModel(string)
                executePendingBindings()
            }
        }
    }
}