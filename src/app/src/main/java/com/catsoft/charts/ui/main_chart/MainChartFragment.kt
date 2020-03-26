package com.catsoft.charts.ui.main_chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.catsoft.charts.R
import com.catsoft.charts.databinding.FragmentMainChartBinding
import com.catsoft.charts.di.Injectable
import com.catsoft.charts.ui.base.BaseFragment
import com.catsoft.charts.utils.observe
import com.scichart.charting.model.dataSeries.XyDataSeries
import com.scichart.charting.visuals.SciChartSurface
import com.scichart.charting.visuals.axes.AxisTitlePlacement
import com.scichart.charting.visuals.axes.IAxis
import com.scichart.charting.visuals.pointmarkers.EllipsePointMarker
import com.scichart.charting.visuals.renderableSeries.IRenderableSeries
import com.scichart.drawing.utility.ColorUtil
import com.scichart.extensions.builders.SciChartBuilder
import java.util.*

class MainChartFragment : BaseFragment<FragmentMainChartBinding>(), Injectable {

    private lateinit var surface: SciChartSurface

    private lateinit var sciChartBuilder: SciChartBuilder

    private lateinit var dataSeries: XyDataSeries<Date, Double>

    private val viewModel: MainChartViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun getViewBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainChartBinding =
        FragmentMainChartBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        initSciChartSurface()

        observe(viewModel.data) {
            dataSeries.append(it.date, it.value)
            surface.zoomExtentsX()
        }
    }

    private fun initSciChartSurface() {
        surface = SciChartSurface(requireContext())
        val chartLayout = viewBinding.chartSurfaceLayout
        chartLayout.addView(surface)

        SciChartBuilder.init(requireContext())

        sciChartBuilder = SciChartBuilder.instance()

        initXAxis()

        initYAxis()

        initZeroLine()

        initModifiers()

        initDataSeries()
    }

    private fun initXAxis() {
        val visibleRangeMin = Calendar.getInstance().apply { this.add(Calendar.SECOND, -10) }.time
        val visibleRangeMax = Calendar.getInstance().apply { this.add(Calendar.SECOND, 30) }.time

        val xAxis: IAxis = sciChartBuilder.newDateAxis()
            .withVisibleRange(visibleRangeMin, visibleRangeMax)
            .withAxisTitle(getString(R.string.x_axis_name))
            .build()

        surface.xAxes.add(xAxis)
    }

    private fun initYAxis() {
        val yMin = -5.0
        val yMax = 5.0

        val yAxis: IAxis = sciChartBuilder.newNumericAxis()
            .withVisibleRange(yMin, yMax)
            .withAxisTitle(getString(R.string.y_axis_name))
            .withAxisTitlePlacement(AxisTitlePlacement.Left)
            .build()

        surface.yAxes.add(yAxis)
    }

    private fun initModifiers() {
        val chartModifiers = sciChartBuilder.newModifierGroup()
            .withModifier(HorizontalRolloverModifier(sciChartBuilder))
            .withPinchZoomModifier().withReceiveHandledEvents(true).build()
            .withZoomPanModifier().withReceiveHandledEvents(true).build()
            .build()

        surface.chartModifiers.add(chartModifiers)
    }

    private fun initZeroLine() {
        val horizontalLineAnnotation = sciChartBuilder.newHorizontalLineAnnotation()
            .withYValue(0.0)
            .build()

        surface.annotations.add(horizontalLineAnnotation)
    }

    private fun initDataSeries() {
        val pointMarker =
            sciChartBuilder.newPointMarker(EllipsePointMarker())
                .withSize(5, 5)
                .withStroke(ColorUtil.argb(255, 176, 196, 222), 2f)
                .withFill(ColorUtil.argb(255, 70, 130, 180))
                .build()


        dataSeries = XyDataSeries(Date::class.java, Double::class.javaObjectType)

        dataSeries.append(Calendar.getInstance().time, 0.0)

        val scatterSeries: IRenderableSeries = sciChartBuilder.newScatterSeries()
            .withDataSeries(dataSeries)
            .withPointMarker(pointMarker)
            .withStrokeStyle(ColorUtil.argb(0xFF, 0x27, 0x9B, 0x27))
            .build()

        surface.renderableSeries.add(scatterSeries)
    }
}