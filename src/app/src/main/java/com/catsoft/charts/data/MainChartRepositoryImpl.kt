package com.catsoft.charts.data

import com.catsoft.charts.domain.MainChartRepository
import com.catsoft.charts.domain.PointDto
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainChartRepositoryImpl(private val scheduler: Scheduler = Schedulers.io()) : MainChartRepository {

    private var _lastValue = 0.0

    override fun observeNewData(): Flowable<PointDto> {
        return Flowable.interval(1, TimeUnit.SECONDS)
            .map { PointDto(Calendar.getInstance().time, getNextValue()) }
            .subscribeOn(scheduler)
    }

    private fun getNextValue() : Double {
        val nextValue = Random.nextDouble(-1.0, 1.0)
        val answer = _lastValue + nextValue
        _lastValue = answer
        return answer
    }
}