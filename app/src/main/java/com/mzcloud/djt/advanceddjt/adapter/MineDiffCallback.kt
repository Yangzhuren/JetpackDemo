package com.mzcloud.djt.advanceddjt.adapter

import androidx.recyclerview.widget.DiffUtil

class MineDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}