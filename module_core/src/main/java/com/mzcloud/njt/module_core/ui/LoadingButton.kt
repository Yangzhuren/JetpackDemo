package com.mzcloud.njt.module_core.ui

import android.annotation.TargetApi
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import com.mzcloud.njt.module_core.R

class LoadingButton(context: Context, attributeSet: AttributeSet?, defStyle: Int = 0) : RelativeLayout(context, attributeSet, defStyle) {
    var tvText: TextView
    var pb: ProgressBar
    var container: RelativeLayout

    constructor(context: Context) : this(context, null, 0)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_loading_button, this, true)
        tvText = findViewById(R.id.text)
        pb = findViewById(R.id.pb)
        container = findViewById(R.id.container)

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.LoadingButton)
        val text = typedArray.getString(R.styleable.LoadingButton_text)
        val textColor = typedArray.getColor(R.styleable.LoadingButton_textColor, Color.BLACK)
        val textSize = typedArray.getDimension(R.styleable.LoadingButton_textSize, 18f)
        val loading = typedArray.getBoolean(R.styleable.LoadingButton_loading, false)
        val background = typedArray.getColor(R.styleable.LoadingButton_backgroundColor, Color.WHITE)
        val tintList = typedArray.getColorStateList(R.styleable.LoadingButton_textColor)
        if (!TextUtils.isEmpty(text))
            tvText.text = text
        tvText.setTextColor(textColor)
        tvText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        loading(loading)
        container.setBackgroundColor(background)
        setTintColor(tintList)
        typedArray.recycle()
    }

    @TargetApi(21)
    private fun setTintColor(colorStateList: ColorStateList?) {
        pb.indeterminateTintList = colorStateList
    }

    fun loading(loading: Boolean = true) {
        if (loading) {
            tvText.visibility = View.INVISIBLE
            pb.visibility = View.VISIBLE
            isClickable = false
        } else {
            tvText.visibility = View.VISIBLE
            pb.visibility = View.INVISIBLE
            isClickable = true
        }
    }
}