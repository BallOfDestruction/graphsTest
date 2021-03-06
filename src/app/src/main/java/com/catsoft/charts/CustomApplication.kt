﻿package com.catsoft.charts

import com.catsoft.charts.di.AppInjector
import com.scichart.charting.visuals.SciChartSurface
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CustomApplication : android.app.Application(), HasAndroidInjector {

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)

        val licenseKey = this.resources.getString(R.string.sci_chart_key)
        SciChartSurface.setRuntimeLicenseKey(licenseKey)
    }
}