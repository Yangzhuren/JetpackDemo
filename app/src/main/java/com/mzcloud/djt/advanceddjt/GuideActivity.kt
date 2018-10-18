package com.mzcloud.djt.advanceddjt

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintro.AppIntro2Fragment
import com.mzcloud.djt.advanceddjt.data.Configuration
import com.mzcloud.djt.advanceddjt.injector.ConfigInjector
import com.mzcloud.djt.advanceddjt.utils.AppUtils
import com.mzcloud.djt.advanceddjt.viewmodels.ConfigListViewModel

class GuideActivity : AppIntro2() {

    companion object {
        fun actionStart(context: Activity) {
            val intent = Intent(context, GuideActivity::class.java)
            context.startActivity(intent)
            context.finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addSlide(AppIntro2Fragment.newInstance("title1", "description1", R.drawable.banner1, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntro2Fragment.newInstance("title2", "description2", R.drawable.banner2, resources.getColor(R.color.colorPrimary)))
        addSlide(AppIntro2Fragment.newInstance("title3", "description3", R.drawable.banner3, resources.getColor(R.color.colorPrimary)))
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        toLogin()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        toLogin()
    }

    private fun toLogin() {
        LoginActivity.actionStart(this)
    }
}
