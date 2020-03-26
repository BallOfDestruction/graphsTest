package com.catsoft.charts.di.module

import com.catsoft.charts.data.MainChartRepositoryImpl
import com.catsoft.charts.domain.MainChartRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun provideMainChartRepo(): MainChartRepository =
        MainChartRepositoryImpl()
}

