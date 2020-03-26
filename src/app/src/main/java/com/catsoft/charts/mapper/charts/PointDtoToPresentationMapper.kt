package com.catsoft.charts.mapper.charts

import com.catsoft.charts.domain.PointDto
import com.catsoft.charts.mapper.IMapper
import com.catsoft.charts.ui.main_chart.PointPresentationDto

class PointDtoToPresentationMapper : IMapper<PointDto, PointPresentationDto> {
    override fun map(from: PointDto): PointPresentationDto {
        return PointPresentationDto(from.date, from.value)
    }
}