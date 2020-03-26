package com.catsoft.charts.di.module

import com.catsoft.charts.ui.main_chart.MainChartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeMainChartFragment(): MainChartFragment
}