package com.catsoft.charts.mapper

interface IMapper<TSource, TDestination> {
    fun map(from : TSource) : TDestination
}