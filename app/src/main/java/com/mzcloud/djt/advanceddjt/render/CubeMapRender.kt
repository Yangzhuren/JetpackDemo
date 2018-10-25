package com.mzcloud.djt.advanceddjt.render

import android.opengl.GLSurfaceView
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.LatLng
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class CubeMapRender(private val amap:AMap,private val center:LatLng):GLSurfaceView.Renderer {
    private val offset = 0.0005F
    private val lastTime = 0L

    init {
        amap.moveCamera(CameraUpdateFactory.newLatLngZoom(center,15f))
    }

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {

    }

    override fun onDrawFrame(gl: GL10?) {

    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
    }
}