package com.catsoft.charts.ui.main_chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.catsoft.charts.databinding.FragmentMainChartBinding
import com.catsoft.charts.di.Injectable
import com.catsoft.charts.ui.base.BaseFragment
import com.scichart.charting.visuals.SciChartSurface
import com.scichart.charting.visuals.annotations.HorizontalAnchorPoint
import com.scichart.charting.visuals.annotations.VerticalAnchorPoint
import com.scichart.charting.visuals.axes.AxisTitlePlacement
import com.scichart.charting.visuals.axes.IAxis
import com.scichart.drawing.utility.ColorUtil
import com.scichart.extensions.builders.SciChartBuilder


class MainChartFragment : BaseFragment<FragmentMainChartBinding>(), Injectable {
    private val viewModel: MainChartViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun getViewBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainChartBinding =
        FragmentMainChartBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val surface = SciChartSurface(requireContext())
        val chartLayout = viewBinding.chartSurfaceLayout
        chartLayout.addView(surface)
        SciChartBuilder.init(requireContext())

        val sciChartBuilder = SciChartBuilder.instance()
        val xAxis: IAxis = sciChartBuilder.newNumericAxis()
            .withAxisTitle("X Axis Title")
            .withVisibleRange(-5.0, 15.0)
            .build()

        val yAxis: IAxis = sciChartBuilder.newNumericAxis()
            .withAxisTitle("Y Axis Title")
            .withAxisTitlePlacement(AxisTitlePlacement.Left)
            .withVisibleRange(0.0, 100.0)
            .build()

        val textAnnotation = sciChartBuilder.newTextAnnotation()
            .withX1(5.0)
            .withY1(55.0)
            .withText("Hello World!")
            .withHorizontalAnchorPoint(HorizontalAnchorPoint.Center)
            .withVerticalAnchorPoint(VerticalAnchorPoint.Center)
            .withFontStyle(20f, ColorUtil.White)
            .build()

        val chartModifiers = sciChartBuilder.newModifierGroup()
            .withPinchZoomModifier().withReceiveHandledEvents(true).build()
            .withZoomPanModifier().withReceiveHandledEvents(true).build()
            .build()

        surface.yAxes.add(yAxis)
        surface.xAxes.add(xAxis)
        surface.annotations.add(textAnnotation)
        surface.chartModifiers.add(chartModifiers)
    }
}