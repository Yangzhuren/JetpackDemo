package com.mzcloud.djt.advanceddjt.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.amap.api.maps.AMap
import com.amap.api.maps.model.Marker
import com.mzcloud.djt.advanceddjt.R
import com.rey.material.widget.ImageView
import com.rey.material.widget.TextView

class MapInfoWindowNavigationAdapter(private val context: Context?, private val function: Function1<Marker?, Unit>) : AMap.InfoWindowAdapter {
    override fun getInfoContents(p0: Marker?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.view_navigation_window, null)
        view.findViewById<TextView>(R.id.tv_title).text = p0?.title
        view.findViewById<TextView>(R.id.tv_snip).text = p0?.snippet
        view.findViewById<ImageView>(R.id.iv_navigation).setOnClickListener {
            function.invoke(p0)
        }
        return view
    }

    override fun getInfoWindow(p0: Marker?): View? = null


}