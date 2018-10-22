package com.mzcloud.njt.module_core.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.mzcloud.njt.module_core.R
import com.mzcloud.njt.module_core.adapter.BottomSheetAdapter
import com.rey.material.app.BottomSheetDialog

object Dialogs {
    fun createBottomSheetDialog(context: Context, data: List<String>, onItemSelected: OnItemSelected?): BottomSheetDialog {
        val bottomSheetDialog = BottomSheetDialog(context)
        val view = LayoutInflater.from(context).inflate(R.layout.view_rolechoose_bottomsheet, null)
        view.findViewById<View>(R.id.in_cancel).setOnClickListener {
            bottomSheetDialog.dismiss()
            onItemSelected?.onItemSelected(it, -1)
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.role_list)
        val bottomSheetAdapter = BottomSheetAdapter()
        bottomSheetAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { _, contentView, position ->
            bottomSheetDialog.dismiss()
            onItemSelected?.onItemSelected(contentView, position)
        }
        recyclerView.adapter = bottomSheetAdapter
        bottomSheetAdapter.setNewData(data)
        bottomSheetDialog.contentView(view)
        return bottomSheetDialog
    }

    interface OnItemSelected {
        fun onItemSelected(view: View, index: Int)
    }
}