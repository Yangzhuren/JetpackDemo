package com.mzcloud.djt.advanceddjt.ui.farmmain

import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProviders
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.MapView
import com.amap.api.maps.model.MyLocationStyle
import com.mzcloud.djt.advanceddjt.R
import com.mzcloud.djt.advanceddjt.databinding.FarmMainFragmentBinding
import com.mzcloud.djt.advanceddjt.utils.toast
import com.mzcloud.djt.advanceddjt.viewmodels.FarmMainViewModel
import com.mzcloud.njt.module_core.ui.BaseFragment

class FarmMainFragment : BaseFragment() {
    private lateinit var mMap: MapView
    private lateinit var mAMap: AMap
    private lateinit var mLocationClient: AMapLocationClient;
    private val mLocationListener = AMapLocationListener {
        if (it.errorCode == 0) {

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

}
