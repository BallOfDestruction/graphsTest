package com.catsoft.charts.ui.main_chart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.catsoft.charts.domain.MainChartRepository
import com.catsoft.charts.mapper.charts.PointDtoToPresentationMapper
import com.catsoft.charts.ui.base.BaseViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MainChartViewModel @Inject constructor(
    mainChartRepository: MainChartRepository,
    mapper: PointDtoToPresentationMapper) : BaseViewModel() {

    private val _data = MutableLiveData<PointPresentationDto>()
    val data: LiveData<PointPresentationDto> = _data

    init {
        mainChartRepository.observeNewData()
            .observeOn(AndroidSchedulers.mainThread())
            .map(mapper::map)
            .subscribe { _data.postValue(PointPresentationDto(it.date, it.value)) }
            .addTo(compositeDisposable)
    }
}