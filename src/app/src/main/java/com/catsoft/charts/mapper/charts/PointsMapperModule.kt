package com.catsoft.charts.mapper.charts

import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class PointsMapperModule {

    @Singleton
    @Provides
    fun provideDtoToPresentationMapper(): PointDtoToPresentationMapper = PointDtoToPresentationMapper()
}