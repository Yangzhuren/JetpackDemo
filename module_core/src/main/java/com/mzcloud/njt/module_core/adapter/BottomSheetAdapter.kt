package com.mzcloud.njt.module_core.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.mzcloud.njt.module_core.R
import com.mzcloud.njt.module_core.utils.getNonNullText

class BottomSheetAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_item_bttomsheet) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper!!.setText(R.id.text, getNonNullText(item))
    }
}