package com.mzcloud.djt.advanceddjt.ui.farmmain

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.*
import com.amap.api.navi.AmapNaviPage
import com.amap.api.navi.AmapNaviParams
import com.amap.api.navi.AmapNaviType
import com.amap.api.navi.INaviInfoCallback
import com.mzcloud.djt.advanceddjt.R
import com.mzcloud.djt.advanceddjt.databinding.FarmMainFragmentBinding
import com.mzcloud.djt.advanceddjt.utils.toast
import com.mzcloud.djt.advanceddjt.viewmodels.FarmMainViewModel
import com.mzcloud.njt.module_core.ui.BaseFragment
import com.mzcloud.djt.advanceddjt.adapter.MapInfoWindowNavigationAdapter
import java.util.*


class FarmMainFragment : BaseFragment() {
    private lateinit var mMap: MapView
    private lateinit var mAMap: AMap
    private lateinit var mLocationClient: AMapLocationClient
    private var mCurrentPosition: LatLng? = null
    private val mLocationListener = AMapLocationListener {
        if (it.errorCode == 0) {
            mCurrentPosition = LatLng(it.latitude, it.longitude)
        } else {
            toast(it.errorInfo)
        }
    }

    companion object {
        fun newInstance() = FarmMainFragment()
    }

    private lateinit var viewModel: FarmMainViewModel

    override fun bindLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        val binding = FarmMainFragmentBinding.inflate(inflater, container, false)
        initMapAndLocation(binding)
        setHasOptionsMenu(true)
        return binding.root
    }


    private fun initMapAndLocation(binding: FarmMainFragmentBinding) {
        mMap = binding.map
        mAMap = mMap.map

        // 设置定位蓝点
        val mLocationStyle = MyLocationStyle()
        mLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE)
        mLocationStyle.showMyLocation(true)
        mAMap.myLocationStyle = mLocationStyle
        mAMap.isMyLocationEnabled = true
        mAMap.uiSettings.isMyLocationButtonEnabled = true

        // 设置定位监听
        mLocationClient = AMapLocationClient(activity)
        mLocationClient.setLocationListener(mLocationListener)

        // 设置场景
        val locationOptions = AMapLocationClientOption()
        locationOptions.locationPurpose = AMapLocationClientOption.AMapLocationPurpose.Transport // 定位模式
        locationOptions.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy  //定位精度
        locationOptions.interval = 5000  // 定位间隔
        locationOptions.isNeedAddress = false  //地址描述信息
        mLocationClient.setLocationOption(locationOptions)
        mLocationClient.startLocation()
    }

    override fun initData(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(FarmMainViewModel::class.java)
        mMap.onCreate(savedInstanceState)
        mAMap.mapType = AMap.MAP_TYPE_SATELLITE

        addMarks()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_amap_type, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        mAMap.isTrafficEnabled = false
        return when (item?.itemId) {
            R.id.menu_map -> {
                mAMap.mapType = AMap.MAP_TYPE_NORMAL
                true
            }
            R.id.menu_rocket -> {
                mAMap.mapType = AMap.MAP_TYPE_SATELLITE
                true
            }
            R.id.menu_night -> {
                mAMap.mapType = AMap.MAP_TYPE_NIGHT
                true
            }
            R.id.menu_traffic -> {
                mAMap.mapType = AMap.MAP_TYPE_BUS
                mAMap.isTrafficEnabled = true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mMap.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        mMap.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMap.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMap.onSaveInstanceState(outState)
    }

    private fun addMarks() {
        val markerOptionsList = arrayListOf<MarkerOptions>()
        for (i in 1..100) {
            var randomTN = Random().nextDouble() * 0.5
            var randomGN = Random().nextDouble() * 0.5
            val randomT = Random().nextFloat()
            val randomG = Random().nextFloat()
            if (randomT < 0.5f) {
                randomTN *= -1
            }
            if (randomG < 0.5f) {
                randomGN *= -1
            }
            var latitude = 31.297892 + randomTN
            val longitude = 121.437273 + randomGN

            val name = "窝$i"
            val markerOptions = MarkerOptions().position(LatLng(latitude, longitude)).title(name).snippet("$name:$latitude,$longitude")
            markerOptionsList.add(markerOptions)
        }
        mAMap.setInfoWindowAdapter(MapInfoWindowNavigationAdapter(context) { marker ->
            val options = marker?.options
            val start = Poi("我", mCurrentPosition, "")
            val end = Poi(options?.title, options?.position, "")
            AmapNaviPage.getInstance().showRouteActivity(context, AmapNaviParams(start, null, end, AmapNaviType.DRIVER), null)
        })
        mAMap.addMarkers(markerOptionsList, true)
    }
}
