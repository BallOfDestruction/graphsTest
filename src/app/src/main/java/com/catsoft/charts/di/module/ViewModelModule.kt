package com.catsoft.charts.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.catsoft.charts.di.CustomViewModelFactory
import com.catsoft.charts.di.ViewModelKey
import com.catsoft.charts.ui.main_chart.MainChartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainChartViewModel::class)
    abstract fun bindMarketListViewModel(mainChartViewModel: MainChartViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: CustomViewModelFactory): ViewModelProvider.Factory
}