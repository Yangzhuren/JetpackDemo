package com.mzcloud.djt.advanceddjt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.mzcloud.djt.advanceddjt.databinding.FarmMainActivityBinding
import java.util.*

class FarmMainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private var prePressedTimestamp: Long = 0L
    private val timeDistance = 2000L

    companion object {
        fun actionStart(activity: Activity) {
            activity.startActivity(Intent(activity, FarmMainActivity::class.java))
            activity.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: FarmMainActivityBinding = DataBindingUtil.setContentView(this, R.layout.farm_main_activity)
        drawerLayout = binding.dlDrawer
        navController = Navigation.findNavController(this, R.id.fragment)
        navController.setGraph(R.navigation.nav_farm)
        setSupportActionBar(binding.tlToolbar)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        binding.nvMenu.setupWithNavController(navController)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_farm_main_menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(drawerLayout, Navigation.findNavController(this, R.id.fragment))
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else if (navController.currentDestination?.id === R.id.home_fragment) {
            exitApp()
        } else
            super.onBackPressed()
    }

    /**
     * 退出应用
     */
    private fun exitApp() {
        val currentTimestamp = Calendar.getInstance().timeInMillis
        if (currentTimestamp - prePressedTimestamp < timeDistance) {
            super.onBackPressed()
        } else {
            prePressedTimestamp = currentTimestamp
            Toast.makeText(this, resources.getString(R.string.toast_repress_exit), Toast.LENGTH_SHORT).show()
        }
    }

}
