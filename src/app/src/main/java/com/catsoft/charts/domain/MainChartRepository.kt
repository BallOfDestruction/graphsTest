package com.catsoft.charts.domain

import io.reactivex.Flowable

interface MainChartRepository {
    fun observeNewData() : Flowable<PointDto>
}

